package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.PostRequest;
import com.mangalaxy.mango.domain.dto.response.PostResponse;
import com.mangalaxy.mango.domain.entity.Post;
import com.mangalaxy.mango.domain.entity.Topic;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.repository.PostRepository;
import com.mangalaxy.mango.repository.TopicRepository;
import com.mangalaxy.mango.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final TopicRepository topicRepository;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  @Override
  public Page<PostResponse> fetchAllPosts(Pageable pageable) {
    Page<Post> postPage = postRepository.findAll(pageable);
    log.info("Fetched post page with total elements: {}", postPage.getTotalElements());
    return postPage.map(this::mapToDto);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<PostResponse> fetchAllPostsByTopicId(Integer topicId, Pageable pageable) {
    Page<Post> postPage = postRepository.findAllByTopic_Id(topicId, pageable);
    log.info("Fetched post page with topic id = {} and total elements: {}", topicId, postPage.getTotalElements());
    return postPage.map(this::mapToDto);
  }

  @Transactional(readOnly = true)
  @Override
  public PostResponse fetchPostById(Integer id) {
    Post post = findPost(id);
    log.info("Fetched post instance with id={} and details: {}", id, post);
    return mapToDto(post);
  }

  @Transactional
  @Override
  public PostResponse createNewPost(Integer topicId, PostRequest postRequest) {
    Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
    Post newPost = modelMapper.map(postRequest, Post.class);
    topic.addPost(newPost);
    newPost = postRepository.save(newPost);
    log.info("The post instance was saved as {}", newPost);
    return mapToDto(newPost);
  }

  @Transactional
  @Override
  public PostResponse updatePostById(Integer id, PostRequest postRequest) {
    Post post = findPost(id);
    modelMapper.map(postRequest, post);
    Post updatedPost = postRepository.save(post);
    log.info("The post instance was updated as: {}", updatedPost);
    return mapToDto(updatedPost);
  }

  // Helper private methods
  private Post findPost(Integer id) {
    return postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }

  private PostResponse mapToDto(Post post) {
    return modelMapper.map(post, PostResponse.PostResponseBuilder.class).build();
  }
}

package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.PostRequest;
import com.mangalaxy.mango.domain.dto.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
  Page<PostResponse> fetchAllPosts(Pageable pageable);
  Page<PostResponse> fetchAllPostsByTopicId(Integer topicId, Pageable pageable);
  PostResponse fetchPostById(Integer id);
  PostResponse createNewPost(Integer topicId, PostRequest postRequest);
  PostResponse updatePostById(Integer id, PostRequest postRequest);
}

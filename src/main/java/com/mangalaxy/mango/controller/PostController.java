package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.PostRequest;
import com.mangalaxy.mango.domain.dto.response.PostResponse;
import com.mangalaxy.mango.service.PostService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(tags = "Posts API", description = "Provides CRUD and others operations for post resource")
@RequiredArgsConstructor
@RestController
public class PostController {
  private final PostService postService;

  @GetMapping("/api/v1/posts")
  public ResponseEntity<Page<PostResponse>> getAllPosts(Pageable pageable) {
    Page<PostResponse> posts = postService.fetchAllPosts(pageable);
    return ResponseEntity.ok(posts);
  }

  @GetMapping("/api/v1/topics/{topicId}/posts")
  public ResponseEntity<Page<PostResponse>> getAllPostsByTopic(@PathVariable Integer topicId, Pageable pageable) {
    Page<PostResponse> posts = postService.fetchAllPostsByTopicId(topicId, pageable);
    return ResponseEntity.ok(posts);
  }

  @GetMapping("/api/v1/posts/{postId}")
  public ResponseEntity<PostResponse> getSpecifiedPost(@PathVariable Integer postId) {
    PostResponse postResponse = postService.fetchPostById(postId);
    return ResponseEntity.ok(postResponse);
  }

  @PostMapping("/api/v1/topics/{topicId}/posts")
  public ResponseEntity<PostResponse> createNewPostWithTopic(@PathVariable Integer topicId,
                                                             @Validated @RequestBody PostRequest postRequest) {
    PostResponse createdPost = postService.createNewPost(topicId, postRequest);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{postId}")
          .buildAndExpand(createdPost.getId())
          .toUri();
    return ResponseEntity.created(location).body(createdPost);
  }

  @PutMapping("/api/v1/posts/{postId}")
  public ResponseEntity<PostResponse> updateSpecifiedPost(@PathVariable Integer postId,
                                                          @Validated @RequestBody PostRequest postRequest) {
    PostResponse postResponse = postService.updatePostById(postId, postRequest);
    return ResponseEntity.ok(postResponse);
  }

  @GetMapping("/api/v1/topics/{topicId}/posts/related")
  public ResponseEntity<List<PostResponse>> getRelatedPostsByTopic(@PathVariable Integer topicId) {
    Page<PostResponse> postPage = postService.fetchAllPostsByTopicId(topicId, PageRequest.of(0, 3));
    return ResponseEntity.ok(postPage.toList());
  }

  @GetMapping("/api/v1/posts/recent")
  public List<String> getRecentPostHeadlines() {
    // TODO: Implement business logic to fetch only 5 recent post headlines
    return null;
  }

}

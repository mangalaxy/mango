package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.response.TopicResponse;
import com.mangalaxy.mango.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Topics API", produces = "application/json", consumes = "application/json")
@RestController
@RequiredArgsConstructor
public class TopicController {
  private final TopicService topicService;

  @ApiOperation(value = "Retrieves a list of all available blog topics", responseContainer = "List")
  @ApiResponse(code = 200, message = "Returns a list of topics", responseContainer = "List")
  @GetMapping("/api/v1/topics")
  public ResponseEntity<List<TopicResponse>> getAllTopics() {
    List<TopicResponse> topics = topicService.getAllTopics();
    return ResponseEntity.ok(topics);
  }
}

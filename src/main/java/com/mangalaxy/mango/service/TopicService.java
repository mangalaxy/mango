package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.TopicResponse;

import java.util.List;

public interface TopicService {
  List<TopicResponse> getAllTopics();
}

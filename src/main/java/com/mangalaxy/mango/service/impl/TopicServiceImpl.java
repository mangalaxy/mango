package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.response.TopicResponse;
import com.mangalaxy.mango.domain.entity.Topic;
import com.mangalaxy.mango.repository.TopicRepository;
import com.mangalaxy.mango.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {
  private final TopicRepository topicRepository;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  @Override
  public List<TopicResponse> getAllTopics() {
    Iterable<Topic> topics = topicRepository.findAll();
    return StreamSupport.stream(topics.spliterator(), false)
          .map(topic -> modelMapper.map(topic, TopicResponse.class))
          .collect(toList());
  }
}

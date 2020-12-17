package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.TopicResponse;
import com.mangalaxy.mango.domain.entity.Topic;
import com.mangalaxy.mango.repository.TopicRepository;
import com.mangalaxy.mango.service.impl.TopicServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {
  @Mock
  private TopicRepository topicRepository;
  private TopicService topicService;

  private Topic topic1;
  private Topic topic2;

  @BeforeEach
  void setUp() {
    ModelMapper modelMapper = new ModelMapper();
    topicService = new TopicServiceImpl(topicRepository, modelMapper);
    topic1 = new Topic(1, "Interview process", null);
    topic2 = new Topic(2, "Job Search", null);
  }

  @Test
  @DisplayName("Get a list of two topics")
  void shouldFindAllTopics() {
    TopicResponse expectedTopic1 = new TopicResponse(1, "Interview process");
    TopicResponse expectedTopic2 = new TopicResponse(2, "Job Search");
    // given
    when(topicRepository.findAll()).thenReturn(Lists.newArrayList(topic1, topic2));
    // when
    List<TopicResponse> topicList = topicService.getAllTopics();
    // then
    verify(topicRepository).findAll();
    assertEquals(2, topicList.size());
    assertEquals(expectedTopic1, topicList.get(0));
    assertEquals(expectedTopic1.getId(), topicList.get(0).getId());
    assertEquals(expectedTopic1.getTitle(), topicList.get(0).getTitle());
    assertEquals(expectedTopic2, topicList.get(1));
    assertEquals(expectedTopic2.getId(), topicList.get(1).getId());
    assertEquals(expectedTopic2.getTitle(), topicList.get(1).getTitle());
  }
}
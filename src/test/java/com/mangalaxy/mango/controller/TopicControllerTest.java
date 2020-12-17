package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.response.TopicResponse;
import com.mangalaxy.mango.service.TopicService;
import lombok.SneakyThrows;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = TopicController.class,
      useDefaultFilters = false,
      includeFilters = {
            @ComponentScan.Filter(
                  type = FilterType.ASSIGNABLE_TYPE,
                  value = TopicController.class)
      })
@AutoConfigureMockMvc(addFilters = false)
class TopicControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private TopicService topicService;
  private List<TopicResponse> topicList;

  @BeforeEach
  void setUp() {
    TopicResponse topic1 = new TopicResponse(1, "Interview process");
    TopicResponse topic2 = new TopicResponse(2, "Job search");
    TopicResponse topic3 = new TopicResponse(3, "Career advice");
    topicList = Lists.newArrayList(topic1, topic2, topic3);
  }

  @SneakyThrows
  @Test
  @DisplayName("Find a list with 3 topics")
  void shouldReturnAllTopicsAndStatusOk() {
    String expectedBody = objectMapper.writeValueAsString(topicList);
    given(topicService.getAllTopics()).willReturn(topicList);
    mockMvc.perform(get("/api/v1/topics")
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedBody));

    verify(topicService).getAllTopics();
  }

}
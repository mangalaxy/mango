package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.request.QuestionRequest;
import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.AnswerResponse;
import com.mangalaxy.mango.domain.dto.response.QuestionResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Answer;
import com.mangalaxy.mango.domain.entity.Question;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.QuestionRepository;
import com.mangalaxy.mango.service.TalentService;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class TalentControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TalentService talentService;

  @Autowired
  private QuestionRepository questionRepository;

  @Test
  public void getTalentById() throws Exception {
    Long expectedId = 1L;
    String expectedEmail = "test@gmail.com";

    MvcResult result = mockMvc.perform(get("/api/v1/talents/1")).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    TalentResponse response = objectMapper.readValue(responseBody, TalentResponse.class);

    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals(expectedEmail, response.getEmail());
  }

  @Test
  public void getAllTalents() throws Exception {
    int expectedSize = 2;

    MvcResult result = mockMvc.perform(get("/api/v1/talents?page=0&limit=20")).andReturn();
    String responseBody = result.getResponse().getContentAsString();

    HashMap<String, Object> talents = objectMapper.readValue(responseBody, new TypeReference<HashMap<String, Object>>(){});

    Assert.assertEquals(expectedSize, ((List)talents.get("content")).size());
  }

  @Test
  public void createNewTalent() throws Exception {
    String fullName = "Test Name";
    String expectedMail = "createdTalent@gmail.com";
    String expectedPassword = "12345";
    TalentRequest talent = TalentRequest.builder()
          .email(expectedMail)
          .password(expectedPassword)
          .fullName(fullName)
          .build();

    String talentJson = objectMapper.writeValueAsString(talent);

    MvcResult result = mockMvc.perform(post("/api/v1/talents").content(talentJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    TalentResponse response = objectMapper.readValue(responseBody, TalentResponse.class);

    Assert.assertEquals(expectedMail, response.getEmail());
  }

  @Test
  public void updateTalent() throws Exception {
    String updatedEmail = "updated@gmail.com";
    TalentResponse talent = talentService.getTalentById(1L);
    talent.setEmail(updatedEmail);

    String talentJson = objectMapper.writeValueAsString(talent);
    MvcResult result = mockMvc.perform(put("/api/v1/talents/1").content(talentJson).contentType(MediaType.APPLICATION_JSON)).andReturn();

    String response = result.getResponse().getContentAsString();

    TalentResponse updatedTalent = objectMapper.readValue(response, TalentResponse.class);

    Assert.assertEquals(updatedEmail, updatedTalent.getEmail());

  }

  @Test
  public void deleteTalent() throws Exception {
    mockMvc.perform(delete("/api/v1/talents/1")).andExpect(status().is(204));
  }

  @Test
  public void getTalentQuestions() throws Exception {
    int expectedSize = 2;

    MvcResult result = mockMvc.perform(get("/api/v1/talents/1/chat")).andReturn();
    String response = result.getResponse().getContentAsString();

    HashMap<String, Object> chat = objectMapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});

    Assert.assertEquals(expectedSize, ((List) chat.get("content")).size());
  }

  @Test
  public void createQuestionTest() throws Exception {
    String expectedMessage = "Some new Question";

    MvcResult result = mockMvc.perform(post("/api/v1/talents/1/chat")
        .content(expectedMessage))
        .andReturn();

    String response = result.getResponse().getContentAsString();
    QuestionResponse questionResponse = objectMapper.readValue(response, QuestionResponse.class);

    Assert.assertEquals(expectedMessage, questionResponse.getMessage());
    Assert.assertNotNull(questionResponse);
  }

  @Test
  public void createAnswerForQuestionTest() throws Exception {
    String expectedText = "some answer";

    MvcResult result = mockMvc.perform(post("/api/v1/talents/1/chat/questions/1/answers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(expectedText))
        .andReturn();

    String response = result.getResponse().getContentAsString();
    AnswerResponse answerResponse = objectMapper.readValue(response, AnswerResponse.class);

    Assert.assertEquals(expectedText, answerResponse.getMessage());
  }
}
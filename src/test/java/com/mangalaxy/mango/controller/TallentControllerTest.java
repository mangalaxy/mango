package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.dto.response.ProfileResponse;
import com.mangalaxy.mango.dto.response.TalentResponse;
import com.mangalaxy.mango.model.entity.Profile;
import com.mangalaxy.mango.model.entity.Talent;
import com.mangalaxy.mango.service.TalentService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class TallentControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private TalentService talentService;

  @Test
  public void getTalentById() throws Exception {
    Long expectedId = 1L;
    String expectedEmail = "test@gmail.com";

    MvcResult result = mockMvc.perform(get("/api/talents/1")).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    TalentResponse response = objectMapper.readValue(responseBody, TalentResponse.class);

    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals(expectedEmail, response.getEmail());
  }

  @Test
  public void getAllTalents() throws Exception {
    int expectedSize = 2;

    MvcResult result = mockMvc.perform(get("/api/talents?page=0&limit=20")).andReturn();
    String responceBody = result.getResponse().getContentAsString();

    HashMap<String, Object> talents = objectMapper.readValue(responceBody, new TypeReference<HashMap<String, Object>>(){});

    Assert.assertEquals(expectedSize, ((List)talents.get("content")).size());
  }

  @Test
  public void getProfileByTalent() throws Exception {
    Long expectedId = 1L;

    MvcResult result = mockMvc.perform(get("/api/talents/1/profile")).andReturn();
    String responseBody = result.getResponse().getContentAsString();

    ProfileResponse profile = objectMapper.readValue(responseBody, ProfileResponse.class);

    Assert.assertEquals(expectedId, profile.getId());
  }

  @Test
  public void createNewTalent() throws Exception {
    String expectedMail = "createdTalent@gmail.com";
    String exprctedPassword = "12345";
    Talent talent = new Talent();
    talent.setEmail(expectedMail);
    talent.setPassword(exprctedPassword);

    String talentJson = objectMapper.writeValueAsString(talent);

    MvcResult result = mockMvc.perform(post("/api/talents").content(talentJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    TalentResponse response = objectMapper.readValue(responseBody, TalentResponse.class);

    Assert.assertEquals(expectedMail, response.getEmail());
  }
}

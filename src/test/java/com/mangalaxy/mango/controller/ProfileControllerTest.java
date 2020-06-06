package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class ProfileControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void getProfileByTalent() throws Exception {
    Long expectedId = 1L;

    MvcResult result = mockMvc.perform(get("/api/v1/talents/1/profile")).andReturn();
    String responseBody = result.getResponse().getContentAsString();

    ProfileResponse profile = objectMapper.readValue(responseBody, ProfileResponse.class);

    Assert.assertEquals(expectedId, profile.getId());
  }
}

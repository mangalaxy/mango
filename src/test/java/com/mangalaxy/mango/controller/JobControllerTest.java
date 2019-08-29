package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.repository.JobRepository;
import com.mangalaxy.mango.service.JobService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class JobControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private JobService jobService;

  @Test
  public void shouldGetJobsByParams() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/jobs?city=lviv&jobRole=role2")).andReturn();
    String response = result.getResponse().getContentAsString();

    HashMap<String, Object> jobs = objectMapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});

    Assert.assertEquals(1, ((List)jobs.get("content")).size());
  }
}

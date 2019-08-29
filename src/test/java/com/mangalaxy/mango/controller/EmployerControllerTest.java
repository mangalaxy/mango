package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.service.EmployerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
public class EmployerControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private EmployerService employerService;

  @Test
  public void shouldFindEmployersByParams() throws Exception {
    int expectedSize = 2;

    MvcResult result = mockMvc.perform(get("/api/v1/employers")).andReturn();
    String response = result.getResponse().getContentAsString();

    HashMap<String, Object> employers = objectMapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});

    Assert.assertEquals(expectedSize, ((List) employers.get("content")).size());
  }

  @Test
  public void shouldFindEmployerById() throws Exception {
    Long expectedId = 1L;
    String expectedMail = "elon@gmail.com";

    MvcResult result = mockMvc.perform(get("/api/v1/employers/1")).andReturn();
    String response = result.getResponse().getContentAsString();
    EmployerResponse employer = objectMapper.readValue(response, EmployerResponse.class);

    Assert.assertEquals(expectedId, employer.getId());
    Assert.assertEquals(expectedMail, employer.getWorkEmail());
  }

  @Test
  public void shouldCreateEmployer() throws Exception {
    Employer employer = new Employer();
    employer.setWorkEmail("testMail@com");
    employer.setFullName("Test Name");
    employer.setLocation(new Location("Poltava", "UA"));

    String employerRequest = objectMapper.writeValueAsString(employer);
    MvcResult result = mockMvc.perform(post("/api/v1/employers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(employerRequest)).andReturn();

    String response = result.getResponse().getContentAsString();
    EmployerResponse createdEmployer = objectMapper.readValue(response, EmployerResponse.class);

    Assert.assertEquals("testMail@com", createdEmployer.getWorkEmail());
  }

  @Test
  public void shouldUpdateEmployer() throws Exception {
    Long employerId = 1L;
    String expectedMail = "changed@mail.com";
    EmployerResponse employer = employerService.getEmployerById(employerId);
    employer.setWorkEmail(expectedMail);

    String request = objectMapper.writeValueAsString(employer);

    MvcResult result = mockMvc.perform(put("/api/v1/employers/1")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(request)).andReturn();

    String response = result.getResponse().getContentAsString();

    EmployerResponse employerResponse = objectMapper.readValue(response, EmployerResponse.class);

    Assert.assertEquals(employerId, employerResponse.getId());
    Assert.assertEquals(expectedMail, employerResponse.getWorkEmail());
  }

  @Test
  public void shouldDeleteEmployer() throws Exception {
    mockMvc.perform(delete("/api/v1/employers/1")).andExpect(status().is(204));
  }
}
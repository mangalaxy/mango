package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.JobDto;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.repository.JobRepository;
import com.mangalaxy.mango.service.JobService;
import org.assertj.core.api.Assertions;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  @Autowired
  private JobRepository jobRepository;

  @Test
  public void shouldGetJobsByParams() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/jobs?city=lviv&jobRole=role2")).andReturn();
    String response = result.getResponse().getContentAsString();

    HashMap<String, Object> jobs = objectMapper.readValue(response, new TypeReference<HashMap<String, Object>>(){});

    Assert.assertEquals(1, ((List)jobs.get("content")).size());
  }

  @Test
  public void shouldGetJobForEmployerById() throws Exception {
    Long expectedId = 1L;
    String expectedTitle = "Java Developer";

    MvcResult result = mockMvc.perform(get("/api/v1/employers/1/jobs/1")).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    JobDto response = objectMapper.readValue(responseBody, JobDto.class);

    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals(expectedTitle, response.getTitle());
  }

  @Test
  public void shouldGetAllJobsForEmployer() throws Exception {
    int expectedSize = 2;

    MvcResult result = mockMvc.perform(get("/api/v1/employers/1/jobs")).andReturn();
    String responseBody = result.getResponse().getContentAsString();

    HashMap<String, Object> jobs = objectMapper.readValue(responseBody, new TypeReference<HashMap<String, Object>>(){});

    Assert.assertEquals(expectedSize, ((List)jobs.get("content")).size());
  }

  @Test
  public void createNewJob() throws Exception {
    String expectedTitle = "Java Developer";

    LocationRequest locationRequest = LocationRequest.builder()
        .id((short) 1L)
        .country("UA")
        .city("Kyiv")
        .build();
    JobRequest jobRequest = JobRequest.builder()
        .title(expectedTitle)
        .jobRole("role2")
        .location(locationRequest)
        .build();

    String jobJson = objectMapper.writeValueAsString(jobRequest);

    MvcResult result = mockMvc.perform(post("/api/v1/employers/1/jobs").content(jobJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
    String responseBody = result.getResponse().getContentAsString();
    JobDto response = objectMapper.readValue(responseBody, JobDto.class);

    Assert.assertEquals(expectedTitle, response.getTitle());
  }

  @Test
  public void updateJob() throws Exception {
    String expectedTitle = "New Title";
    Long expectedId = 1L;
    JobDto job = jobService.getEmployerJob(expectedId, 1L);
    job.setTitle(expectedTitle);

    String jobJson = objectMapper.writeValueAsString(job);
    MvcResult result = mockMvc.perform(put("/api/v1/employers/1/jobs/1")
          .content(jobJson)
          .contentType(MediaType.APPLICATION_JSON))
          .andReturn();

    String response = result.getResponse().getContentAsString();

    JobDto updatedJob = objectMapper.readValue(response, JobDto.class);

    Assert.assertEquals(expectedTitle, updatedJob.getTitle());
    Assert.assertEquals(expectedId, updatedJob.getId());

  }

  @Test
  public void deleteJob() throws Exception {
    mockMvc.perform(delete("/api/v1/employers/1/jobs/1"))
          .andExpect(status().is(204));
    Optional<Job> job = jobRepository.findByIdAndPublisher_Id(1L, 1L);
    Assertions.assertThat(job.isPresent()).isFalse();
  }

}

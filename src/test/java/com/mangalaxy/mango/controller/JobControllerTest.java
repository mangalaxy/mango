package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.request.SkillRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.dto.response.SkillResponse;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = JobController.class, useDefaultFilters = false,
      includeFilters = {
            @ComponentScan.Filter(
                  type = FilterType.ASSIGNABLE_TYPE,
                  value = JobController.class)
      })
@AutoConfigureMockMvc(addFilters = false)
class JobControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private JobService jobService;

  private JobResponse job1;
  private JobResponse job2;
  private JobResponse job3;
  private JobResponse job4;

  @BeforeEach
  void setUp() {
    LocationResponse location1 = new LocationResponse((short) 1, "Mexico City", "Mexico");
    LocationResponse location2 = new LocationResponse((short) 2, "Austin", "USA");
    LocationResponse location3 = new LocationResponse((short) 3, "Chicago", "USA");

    job1 = JobResponse.builder()
          .id(1L)
          .title("Senior Java Developer")
          .location(location1)
          .jobRoleTitle("Software Engineering")
          .jobType("Full-time")
          .remote(false)
          .relocation(true)
          .visaSponsorship(true)
          .experienceRequired("6-10 years")
          .createdDate(LocalDateTime.now())
          .build();

    job2 = JobResponse.builder()
          .id(2L)
          .title("Business Analyst")
          .location(location2)
          .jobRoleTitle("Data Analytics")
          .jobType("Contract")
          .remote(false)
          .relocation(false)
          .visaSponsorship(false)
          .experienceRequired("2-4 years")
          .createdDate(LocalDateTime.now())
          .build();

    job3 = JobResponse.builder()
          .id(3L)
          .title("Frontend Consultant")
          .location(location1)
          .jobRoleTitle("Software Engineering")
          .jobType("Full-time")
          .remote(false)
          .relocation(true)
          .visaSponsorship(true)
          .experienceRequired("4-6 years")
          .createdDate(LocalDateTime.now())
          .build();

    job4 = JobResponse.builder()
          .id(4L)
          .title("UI Designer")
          .location(location3)
          .jobRoleTitle("Design")
          .jobType("Part-time")
          .remote(true)
          .relocation(false)
          .visaSponsorship(false)
          .experienceRequired("4-6 years")
          .createdDate(LocalDateTime.now())
          .build();

    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
  }

  @Test
  @DisplayName("Find all jobs that match a given city")
  void shouldReturnJobsFilterByCityAndStatusOk() throws Exception {
    String searchingCity = "Mexico City";
    List<JobResponse> jobsList = Lists.newArrayList(job1, job3);
    Page<JobResponse> jobsPage = new PageImpl<>(jobsList);
    String expectedJobs = objectMapper.writeValueAsString(jobsPage);
    given(jobService.findJobsByParams(isNull(), anyString(), any(Pageable.class))).willReturn(jobsPage);
    mockMvc.perform(get("/api/v1/jobs?city={searchingCity}", searchingCity)
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJobs));

    verify(jobService).findJobsByParams(isNull(), anyString(), any(Pageable.class));
  }

  @Test
  @DisplayName("Find all jobs that match a given job role")
  void shouldReturnJobsFilterByJobRoleAndStatusOk() throws Exception {
    String searchingRole = "Design";
    List<JobResponse> jobsList = Lists.newArrayList(job4);
    Page<JobResponse> jobsPage = new PageImpl<>(jobsList);
    String expectedJson = objectMapper.writeValueAsString(jobsPage);
    given(jobService.findJobsByParams(eq(searchingRole), nullable(String.class), any(Pageable.class))).willReturn(jobsPage);
    mockMvc.perform(get("/api/v1/jobs?jobRole={searchingRole}", searchingRole)
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    verify(jobService).findJobsByParams(eq(searchingRole), nullable(String.class), any(Pageable.class));
  }

  @Test
  @DisplayName("Find an employer's job by employer ID and job ID")
  void shouldReturnJobFromEmployerByIdAndStatusOk() throws Exception {
    String expectedJson = objectMapper.writeValueAsString(job2);
    given(jobService.fetchEmployerJob(anyLong(), anyLong())).willReturn(job2);
    mockMvc.perform(get("/api/v1/employers/1/jobs/2")
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    verify(jobService).fetchEmployerJob(anyLong(), anyLong());
  }

  @Test
  @DisplayName("Find all jobs by employer ID")
  void shouldReturnAllJobsForEmployerAndStatusOk() throws Exception {
    List<JobResponse> jobList = Lists.newArrayList(job2, job3, job4);
    Page<JobResponse> jobPage = new PageImpl<>(jobList);
    String expectedJson = objectMapper.writeValueAsString(jobPage);
    given(jobService.fetchAllEmployerJobs(anyLong(), any(Pageable.class))).willReturn(jobPage);
    mockMvc.perform(get("/api/v1/employers/1/jobs")
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    verify(jobService).fetchAllEmployerJobs(anyLong(), any(Pageable.class));
  }

  @Test
  @DisplayName("Return status 404 when employer's job not found")
  void shouldReturnsStatus404WhenJobNotFound() throws Exception {
    Long employerId = 1L;
    Long jobId = 1L;
    willThrow(new ResourceNotFoundException("job", "id", jobId)).given(jobService).fetchEmployerJob(employerId, jobId);
    MvcResult mvcResult = mockMvc.perform(
          get("/api/v1/employers/{employerId}/jobs/{jobId}", employerId, jobId)
                .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound())
          .andReturn();
    verify(jobService).fetchEmployerJob(employerId, jobId);
    assertThat(mvcResult.getResolvedException()).isInstanceOf(ResourceNotFoundException.class);
    assertThat(mvcResult.getResolvedException()).hasMessage("job not found with id : '1'");
  }

  @Test
  @DisplayName("Create a new job by employer and return status 201")
  void shouldCreateNewJobByEmployerIdAndReturnStatus201() throws Exception {
    LocationRequest locationRequest = new LocationRequest((short) 1, "Boston", "USA");
    Set<SkillRequest> skillSet = Sets.newHashSet(
          new SkillRequest("Problem solving"),
          new SkillRequest("DDD"));
    JobRequest jobRequest = JobRequest.builder()
          .title("System Architect")
          .jobRoleTitle("Software Engineering")
          .jobType("Full-time")
          .remote(false)
          .relocation(false)
          .visaSponsorship(false)
          .location(locationRequest)
          .experienceRequired("10+ years")
          .skills(skillSet)
          .build();

    String newJobJson = objectMapper.writeValueAsString(jobRequest);
    given(jobService.createEmployerJob(anyLong(), any(JobRequest.class))).willAnswer((Answer<JobResponse>) invocation -> {
      JobRequest request = invocation.getArgument(1);
      JobResponse jobResponse = JobResponse.builder()
            .id(1L)
            .title(request.getTitle())
            .jobRoleTitle(request.getJobRoleTitle())
            .remote(request.getRemote())
            .relocation(request.getRelocation())
            .visaSponsorship(request.getVisaSponsorship())
            .experienceRequired(request.getExperienceRequired())
            .jobType(request.getJobType())
            .location(new LocationResponse(
                  request.getLocation().getId(),
                  request.getLocation().getCity(),
                  request.getLocation().getCountry()
            ))
            .createdDate(LocalDateTime.now())
            .build();
      return jobResponse;
    });
    mockMvc.perform(post("/api/v1/employers/1/jobs")
          .accept(MediaType.APPLICATION_JSON)
          .content(newJobJson)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isCreated())
          .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/employers/1/jobs/1"));

    ArgumentCaptor<JobRequest> argumentCaptor = ArgumentCaptor.forClass(JobRequest.class);
    verify(jobService).createEmployerJob(anyLong(), argumentCaptor.capture());
    assertThat(argumentCaptor.getValue().getTitle()).isEqualTo("System Architect");
    assertThat(argumentCaptor.getValue().getJobRoleTitle()).isEqualTo("Software Engineering");
  }

  @Test
  @DisplayName("Update the specified job with the new title")
  void shouldUpdateJobWithNewTitleAndReturnStatusOk() throws Exception {
    // prepare data
    String updatedTitle = "Senior Software Engineer";
    LocationRequest locationRequest = new LocationRequest((short) 1, "Boston", "USA");
    Set<SkillRequest> skillSet = Sets.newHashSet(
          new SkillRequest("Problem solving"),
          new SkillRequest("DDD"));
    SkillRequest[] skillArr = skillSet.toArray(new SkillRequest[0]);
    Set<SkillResponse> skillResponses = IntStream.range(0, skillArr.length)
          .mapToObj(index -> new SkillResponse((long) (index + 1), skillArr[index].getName()))
          .collect(Collectors.toSet());
    JobRequest jobRequest = JobRequest.builder()
          .title(updatedTitle)
          .jobRoleTitle("Software Engineering")
          .jobType("Full-time")
          .remote(false)
          .relocation(false)
          .visaSponsorship(false)
          .location(locationRequest)
          .experienceRequired("10+ years")
          .skills(skillSet)
          .build();
    String jobJson = objectMapper.writeValueAsString(jobRequest);
    JobResponse jobResponse = JobResponse.builder()
          .id(1L)
          .title(jobRequest.getTitle())
          .jobRoleTitle(jobRequest.getJobRoleTitle())
          .remote(jobRequest.getRemote())
          .relocation(jobRequest.getRelocation())
          .visaSponsorship(jobRequest.getVisaSponsorship())
          .experienceRequired(jobRequest.getExperienceRequired())
          .jobType(jobRequest.getJobType())
          .location(new LocationResponse(
                jobRequest.getLocation().getId(),
                jobRequest.getLocation().getCity(),
                jobRequest.getLocation().getCountry()
          ))
          .skills(skillResponses)
          .createdDate(LocalDateTime.parse("2019-12-18T16:35:48"))
          .modifiedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
          .build();

    String expectedJson = objectMapper.writeValueAsString(jobResponse);
    given(jobService.updateEmployerJob(anyLong(), anyLong(), any(JobRequest.class))).willReturn(jobResponse);

    mockMvc.perform(put("/api/v1/employers/1/jobs/1")
          .accept(MediaType.APPLICATION_JSON)
          .content(jobJson)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    ArgumentCaptor<JobRequest> argumentCaptor = ArgumentCaptor.forClass(JobRequest.class);
    verify(jobService).updateEmployerJob(anyLong(), anyLong(), argumentCaptor.capture());
    assertThat(argumentCaptor.getValue().getTitle()).isEqualTo(updatedTitle);
    assertThat(argumentCaptor.getValue().getJobRoleTitle()).isEqualTo("Software Engineering");
  }

  @Test
  @DisplayName("Delete specified job from employer and check status 204")
  void shouldDeleteJobByIdFromEmployerAndReturnStatus204() throws Exception {
    doNothing().when(jobService).deleteEmployerJob(anyLong(), anyLong());
    mockMvc.perform(delete("/api/v1/employers/1/jobs/1")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNoContent());
    verify(jobService).deleteEmployerJob(anyLong(), anyLong());
  }
}

package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.request.SkillRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.domain.dto.response.SkillResponse;
import com.mangalaxy.mango.service.JobService;
import com.mangalaxy.mango.util.ResourceNotFoundException;
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
    job1 = new JobResponse();
    job1.setId(1L);
    job1.setTitle("Senior Java Developer");
    job1.setLocationCity("Mexico City");
    job1.setLocationCountry("Mexico");
    job1.setJobRoleTitle("Software Engineering");
    job1.setEmploymentType("Full-time");
    job1.setRemote(false);
    job1.setRelocation(true);
    job1.setVisaSponsorship(true);
    job1.setRequiredExperience("6-10 years");
    job1.setCreatedDate(LocalDateTime.now());

    job2 = new JobResponse();
    job2.setId(2L);
    job2.setTitle("Business Analyst");
    job2.setLocationCity("Austin");
    job2.setLocationCountry("USA");
    job2.setJobRoleTitle("Data Analytics");
    job2.setEmploymentType("Contract");
    job2.setRemote(false);
    job2.setRelocation(false);
    job2.setVisaSponsorship(false);
    job2.setRequiredExperience("2-4 years");
    job2.setCreatedDate(LocalDateTime.now());

    job3 = new JobResponse();
    job3.setId(3L);
    job3.setTitle("Frontend Consultant");
    job3.setLocationCity("Mexico City");
    job3.setLocationCountry("Mexico");
    job3.setJobRoleTitle("Software Engineering");
    job3.setEmploymentType("Full-time");
    job3.setRemote(false);
    job3.setRelocation(true);
    job3.setVisaSponsorship(true);
    job3.setRequiredExperience("4-6 years");
    job3.setCreatedDate(LocalDateTime.now());

    job4 = new JobResponse();
    job4.setId(4L);
    job4.setTitle("UI Designer");
    job4.setLocationCity("Chicago");
    job4.setLocationCountry("USA");
    job4.setJobRoleTitle("Design");
    job4.setEmploymentType("Part-time");
    job4.setRemote(true);
    job4.setRelocation(false);
    job4.setVisaSponsorship(false);
    job4.setRequiredExperience("4-6 years");
    job4.setCreatedDate(LocalDateTime.now());

    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
  }

  @Test
  @DisplayName("Find all jobs that match a given city")
  void shouldReturnJobsFilterByCityAndStatusOk() throws Exception {
    String searchingCity = "Mexico City";
    List<JobResponse> jobsList = Lists.newArrayList(job1, job3);
    Page<JobResponse> jobsPage = new PageImpl<>(jobsList);
    String expectedJobs = objectMapper.writeValueAsString(jobsPage);
    given(jobService.selectJobsByParams(isNull(), anyString(), any(Pageable.class))).willReturn(jobsPage);
    mockMvc.perform(get("/api/v1/jobs?city={searchingCity}", searchingCity)
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJobs));

    verify(jobService).selectJobsByParams(isNull(), anyString(), any(Pageable.class));
  }

  @Test
  @DisplayName("Find all jobs that match a given job role")
  void shouldReturnJobsFilterByJobRoleAndStatusOk() throws Exception {
    String searchingRole = "Design";
    List<JobResponse> jobsList = Lists.newArrayList(job4);
    Page<JobResponse> jobsPage = new PageImpl<>(jobsList);
    String expectedJson = objectMapper.writeValueAsString(jobsPage);
    given(jobService.selectJobsByParams(eq(searchingRole), nullable(String.class), any(Pageable.class))).willReturn(jobsPage);
    mockMvc.perform(get("/api/v1/jobs?jobRole={searchingRole}", searchingRole)
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    verify(jobService).selectJobsByParams(eq(searchingRole), nullable(String.class), any(Pageable.class));
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
    given(jobService.fetchEmployerAllJobs(anyLong(), any(Pageable.class))).willReturn(jobPage);
    mockMvc.perform(get("/api/v1/employers/1/jobs")
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    verify(jobService).fetchEmployerAllJobs(anyLong(), any(Pageable.class));
  }

  @Test
  @DisplayName("Return status 404 when employer's job not found")
  void shouldReturnsStatus404WhenJobNotFound() throws Exception {
    Long employerId = 1L;
    Long jobId = 1L;
    willThrow(new ResourceNotFoundException()).given(jobService).fetchEmployerJob(employerId, jobId);
    MvcResult mvcResult = mockMvc.perform(
          get("/api/v1/employers/{employerId}/jobs/{jobId}", employerId, jobId)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound())
          .andReturn();
    verify(jobService).fetchEmployerJob(employerId, jobId);
    assertThat(mvcResult.getResolvedException()).isInstanceOf(ResourceNotFoundException.class);
    assertThat(mvcResult.getResolvedException()).hasMessage("Resource with specified ID not found");
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
          .jobRole("Software Engineering")
          .employmentType("Full-time")
          .isRemote(false)
          .isRelocate(false)
          .isVisaSponsorship(false)
          .location(locationRequest)
          .requiredExperience("10+ years")
          .skills(skillSet)
          .build();

    String newJobJson = objectMapper.writeValueAsString(jobRequest);
    given(jobService.createEmployerJob(anyLong(), any(JobRequest.class))).willAnswer((Answer<JobResponse>) invocation -> {
      JobRequest request = invocation.getArgument(1);
      JobResponse jobResponse = new JobResponse();
      jobResponse.setId(1L);
      jobResponse.setTitle(request.getTitle());
      jobResponse.setJobRoleTitle(request.getJobRole());
      jobResponse.setRemote(request.getRemote());
      jobResponse.setRelocation(request.getRelocation());
      jobResponse.setVisaSponsorship(request.getVisaSponsorship());
      jobResponse.setRequiredExperience(request.getRequiredExperience());
      jobResponse.setEmploymentType(request.getEmploymentType());
      jobResponse.setLocationCity(request.getLocation().getCity());
      jobResponse.setLocationCountry(request.getLocation().getCountry());
      jobResponse.setCreatedDate(LocalDateTime.now());
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
    assertThat(argumentCaptor.getValue().getJobRole()).isEqualTo("Software Engineering");
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
          .jobRole("Software Engineering")
          .employmentType("Full-time")
          .isRemote(false)
          .isRelocate(false)
          .isVisaSponsorship(false)
          .location(locationRequest)
          .requiredExperience("10+ years")
          .skills(skillSet)
          .build();
    String jobJson = objectMapper.writeValueAsString(jobRequest);
    JobResponse jobResponse = new JobResponse();
    jobResponse.setId(1L);
    jobResponse.setTitle(jobRequest.getTitle());
    jobResponse.setJobRoleTitle(jobRequest.getJobRole());
    jobResponse.setRemote(jobRequest.getRemote());
    jobResponse.setRelocation(jobRequest.getRelocation());
    jobResponse.setVisaSponsorship(jobRequest.getVisaSponsorship());
    jobResponse.setRequiredExperience(jobRequest.getRequiredExperience());
    jobResponse.setEmploymentType(jobRequest.getEmploymentType());
    jobResponse.setLocationCity(jobRequest.getLocation().getCity());
    jobResponse.setLocationCountry(jobRequest.getLocation().getCountry());
    jobResponse.setSkills(skillResponses);
    jobResponse.setCreatedDate(LocalDateTime.parse("2019-12-18T16:35:48"));
    jobResponse.setModifiedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

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
    assertThat(argumentCaptor.getValue().getJobRole()).isEqualTo("Software Engineering");
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

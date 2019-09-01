package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.JobRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JobServiceTest {
  @Autowired
  private JobService jobService;

  @MockBean
  private JobRepository jobRepository;

  @MockBean
  private EmployerRepository employerRepository;

  @Autowired
  private ModelMapper modelMapper;

  private static Job firstMockJob;
  private static Job secondMockJob;
  private static Job thirdMockJob;
  private static Employer mockEmployer;

  @Before
  public void setUp() {
    Location firstLocation = new Location();
    firstLocation.setId(1);
    firstLocation.setCity("Kyiv");
    firstLocation.setCountry("Ukraine");

    mockEmployer = new Employer();
    mockEmployer.setId(1L);
    mockEmployer.setWorkEmail("test@mail.au");
    mockEmployer.setPassword("123456");
    mockEmployer.setFullName("Elon Mask");
    mockEmployer.setLocation(firstLocation);

    Location secondLocation = new Location();
    secondLocation.setId(2);
    secondLocation.setCity("Lviv");
    secondLocation.setCountry("Ukraine");

    firstMockJob = new Job();
    firstMockJob.setId(1L);
    firstMockJob.setTitle("Java Developer");
    firstMockJob.setLocation(firstLocation);
    firstMockJob.setJobRole("role1");
    firstMockJob.setPublisher(mockEmployer);

    secondMockJob = new Job();
    secondMockJob.setId(2L);
    secondMockJob.setTitle("JS Developer");
    secondMockJob.setLocation(secondLocation);
    secondMockJob.setJobRole("role2");
    secondMockJob.setPublisher(mockEmployer);

    thirdMockJob = new Job();
    thirdMockJob.setId(3L);
    thirdMockJob.setTitle("Front End");
    thirdMockJob.setLocation(firstLocation);
    thirdMockJob.setJobRole("role2");
    thirdMockJob.setPublisher(mockEmployer);
  }

  @Test
  public void shouldFindJobsByParams() {
    List<Job> jobs = new ArrayList<>();
    jobs.add(firstMockJob);
    jobs.add(secondMockJob);
    jobs.add(thirdMockJob);

    Pageable pageable = mock(Pageable.class);

    Page<Job> allJobs = new PageImpl(jobs);

    Mockito.when(jobRepository.findAll(pageable)).thenReturn(allJobs);
    Page<JobResponse> jobsList = jobService.getJobsByParameters("role2", "kyiv", pageable);

    verify(jobRepository).findAll(pageable);
    Assert.assertEquals(1, jobsList.getContent().size());
  }

  @Test
  public void shouldGetJobsForEmployer() {
    List<Job> jobs = new ArrayList<>();
    jobs.add(firstMockJob);
    jobs.add(secondMockJob);
    jobs.add(thirdMockJob);

    Pageable pageable = mock(Pageable.class);

    Page<Job> allJobs = new PageImpl(jobs);

    Mockito.when(jobRepository.findAllByPublisher_Id(1L, pageable)).thenReturn(allJobs);
    Page<JobResponse> jobList = jobService.getJobsForEmployer(pageable, 1L);

    verify(jobRepository).findAllByPublisher_Id(1L, pageable);
    Assert.assertEquals(jobList.getContent().size(), jobs.size());
  }

  @Test
  public void shouldGetJobForEmployer() {
    Long expectedId = 1L;
    String expectedTitle = "Java Developer";

    Mockito.when(jobRepository.findByIdAndPublisher_Id(1L, 1L)).thenReturn(firstMockJob);
    JobResponse jobResponse = jobService.findJobByEmployerAndId(1L, 1L);

    verify(jobRepository).findByIdAndPublisher_Id(1L, 1L);
    Assert.assertEquals(expectedId, jobResponse.getId());
    Assert.assertEquals(expectedTitle, jobResponse.getTitle());
  }

  @Test
  public void shouldDeleteJob() {
    Mockito.when(jobRepository.findByIdAndPublisher_Id(1L, 1L)).thenReturn(firstMockJob);
    jobService.deletJob(1L, 1L);
    verify(jobRepository).delete(firstMockJob);
  }
}

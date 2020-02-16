package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.JobDto;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.JobRole;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.JobRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.mockito.Mockito.when;

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

  private Job firstMockJob;
  private Job secondMockJob;
  private Job thirdMockJob;
  private Employer mockEmployer;

  @Before
  public void setUp() {
    Location firstLocation = new Location();
    firstLocation.setId((short)1);
    firstLocation.setCity("Kyiv");
    firstLocation.setCountry("Ukraine");

    mockEmployer = new Employer();
    mockEmployer.setId(1L);
    mockEmployer.setEmail("test@mail.au");
    mockEmployer.setPassword("123456");
    mockEmployer.setFullName("Elon Mask");
    mockEmployer.setLocation(firstLocation);

    Location secondLocation = new Location();
    secondLocation.setId((short)2);
    secondLocation.setCity("Lviv");
    secondLocation.setCountry("Ukraine");


    Employer employer = new Employer();
    employer.setId(1L);
    employer.setFullName("Elon Mask");
    employer.setLocation(firstLocation);

    JobRole jobRole = new JobRole();
    jobRole.setTitle("Software Engineering");

    firstMockJob = new Job();
    firstMockJob.setId(1L);
    firstMockJob.setTitle("Senior Java Developer");
    firstMockJob.setLocation(firstLocation);
    firstMockJob.setJobRole(jobRole);
    firstMockJob.setPublisher(mockEmployer);
    firstMockJob.setPublisher(employer);

    secondMockJob = new Job();
    secondMockJob.setId(2L);
    secondMockJob.setTitle("JS Developer");
    secondMockJob.setLocation(secondLocation);
    secondMockJob.setJobRole(jobRole);
    secondMockJob.setPublisher(mockEmployer);
    secondMockJob.setPublisher(employer);

    thirdMockJob = new Job();
    thirdMockJob.setId(3L);
    thirdMockJob.setTitle("Front End");
    thirdMockJob.setLocation(firstLocation);
    thirdMockJob.setJobRole(jobRole);
    thirdMockJob.setPublisher(mockEmployer);
    thirdMockJob.setPublisher(employer);
  }

  @Test
  public void shouldFindJobsByParams() {
    List<Job> jobs = new ArrayList<>();
    jobs.add(firstMockJob);
    jobs.add(secondMockJob);
    jobs.add(thirdMockJob);

    Pageable pageable = mock(Pageable.class);

    Page<Job> allJobs = new PageImpl<>(jobs);

    when(jobRepository.findAll(pageable)).thenReturn(allJobs);
    Page<JobDto> jobsList = jobService.selectJobsByParams("role2", "Berlin", pageable);

    verify(jobRepository).findAll(pageable);
    Assert.assertEquals(1, jobsList.getContent().size());
  }

  @Test
  public void shouldGetJobsForEmployer() {
    List<Job> jobMocks = new ArrayList<>();
    jobMocks.add(firstMockJob);
    jobMocks.add(secondMockJob);
    jobMocks.add(thirdMockJob);

    Pageable pageable = mock(Pageable.class);

    Page<Job> jobPageMock = new PageImpl<>(jobMocks);

    when(jobRepository.findAllByPublisher_Id(1L, pageable)).thenReturn(jobPageMock);
    Page<JobDto> jobList = jobService.getEmployerAllJobs(1L, pageable);
    verify(jobRepository).findAllByPublisher_Id(1L, pageable);
    Assert.assertEquals(jobList.getContent().size(), jobMocks.size());
  }

  @Test
  public void shouldFindJobForEmployer() {
    Long expectedId = 1L;
    String expectedTitle = "Java Developer";

    when(jobRepository.findByIdAndPublisher_Id(1L, 1L)).thenReturn(Optional.ofNullable(firstMockJob));
    JobDto job = jobService.getEmployerJob(1L, 1L);

    verify(jobRepository).findByIdAndPublisher_Id(1L, 1L);
    Assert.assertEquals(expectedId, job.getId());
    Assert.assertEquals(expectedTitle, job.getTitle());
  }

  @Test
  public void shouldDeleteJob() {
    when(jobRepository.findByIdAndPublisher_Id(1L, 1L)).thenReturn(Optional.ofNullable(firstMockJob));
    jobService.removeEmployerJob(1L, 1L);
    verify(jobRepository).delete(firstMockJob);
  }
}

package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.JobRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JobServiceTest {
  @Autowired
  private JobService jobService;

  @MockBean
  private JobRepository jobRepository;

  private static Job firstMockJob = new Job();
  private static Job secondMockJob = new Job();
  private static Job thirdMockJob = new Job();

  @Before
  public void setUp() {
    Location firstLocation = new Location();
    firstLocation.setId(1);
    firstLocation.setCity("Kyiv");
    firstLocation.setCountry("Ukraine");

    Location secondLocation = new Location();
    secondLocation.setId(2);
    secondLocation.setCity("Lviv");
    secondLocation.setCountry("Ukraine");

    Employer employer = new Employer();
    employer.setId(1L);
    employer.setFullName("Elon Mask");
    employer.setLocation(firstLocation);

    firstMockJob.setId(1L);
    firstMockJob.setTitle("Java Developer");
    firstMockJob.setLocation(firstLocation);
    firstMockJob.setJobRole("role1");
    firstMockJob.setPublisher(employer);

    secondMockJob.setId(2L);
    secondMockJob.setTitle("JS Developer");
    secondMockJob.setLocation(secondLocation);
    secondMockJob.setJobRole("role2");
    secondMockJob.setPublisher(employer);

    thirdMockJob.setId(3L);
    thirdMockJob.setTitle("Front End");
    thirdMockJob.setLocation(firstLocation);
    thirdMockJob.setJobRole("role2");
    thirdMockJob.setPublisher(employer);
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
}

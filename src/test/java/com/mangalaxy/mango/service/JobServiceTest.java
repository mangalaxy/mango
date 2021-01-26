package com.mangalaxy.mango.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Shorts;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.JobRole;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Skill;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.JobRepository;
import com.mangalaxy.mango.repository.JobRoleRepository;
import com.mangalaxy.mango.repository.LocationRepository;
import com.mangalaxy.mango.repository.SkillRepository;
import com.mangalaxy.mango.service.impl.JobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {
  @Mock
  private JobRepository jobRepository;
  @Mock
  private JobRoleRepository jobRoleRepository;
  @Mock
  private EmployerRepository employerRepository;
  @Mock
  private LocationRepository locationRepository;
  @Mock
  private SkillRepository skillRepository;
  private JobService jobService;

  private Job job1;
  private Job job2;
  private Job job3;

  private Employer employer1;
  private JobRole seRole;
  private List<Skill> skills;

  @BeforeEach
  void setUp() {
    ModelMapper modelMapper = new ModelMapper();
    jobService = new JobServiceImpl(jobRepository, jobRoleRepository, employerRepository,
          skillRepository, locationRepository, modelMapper);

    Location location1 = new Location();
    location1.setId(Shorts.checkedCast(1L));
    location1.setCity("Berlin");
    location1.setCountry("Germany");

    Location location2 = new Location();
    location2.setId(Shorts.checkedCast(2L));
    location2.setCity("San Francisco");
    location2.setCountry("USA");

    Location location3 = new Location();
    location3.setId(Shorts.checkedCast(3L));
    location3.setCity("Toronto");
    location3.setCountry("Canada");

    employer1 = new Employer();
    employer1.setId(1L);
    employer1.setFullName("Maria Fisher");
    employer1.setEmail("m.fisher@gmail.com");
    employer1.setPassword("AS3$hj67mf1983");
    employer1.setLocation(location1);


    JobRole jobRole = new JobRole();
    jobRole.setTitle("Software Engineering");

    job1 = new Job();
    job1.setId(1L);
    job1.setTitle("Senior Java Developer");
    job1.setLocation(location1);
    job1.setJobRole(jobRole);
    job1.setPublisher(employer1);

    job2 = new Job();
    job2.setId(2L);
    job2.setTitle("Java Software Engineer");
    job2.setLocation(location2);
    job2.setJobRole(jobRole);
    job2.setPublisher(employer1);

    job3 = new Job();
    job3.setId(3L);
    job3.setTitle("Senior React Developer");
    job3.setLocation(location3);
    job3.setJobRole(jobRole);
    job3.setPublisher(employer1);

    employer1.setJobs(Sets.newHashSet(job1, job2, job3));

    seRole = new JobRole();
    seRole.setId(Short.valueOf("1"));
    seRole.setTitle("Software Engineering");

    Skill skill1 = new Skill();
    skill1.setId(1L);
    skill1.setName("AngularJS");

    Skill skill2 = new Skill();
    skill2.setId(2L);
    skill1.setName("Node.js");

    Skill skill3 = new Skill();
    skill3.setId(3L);
    skill3.setName("Firebase");

    skills = Lists.newArrayList(skill1, skill2, skill3);
  }

  @Test
  @DisplayName("Find all jobs by role - Software Engineering")
  void shouldFilterJobsByJobRole_thenSuccess() {
    // given
    Pageable pageable = PageRequest.of(0, 20);
    Page<Job> jobPage = new PageImpl<>(Lists.newArrayList(job1, job2, job3));
    when(jobRoleRepository.findByTitle("Software Engineering"))
          .thenAnswer((Answer<JobRole>) invocation -> new JobRole((short) 1, invocation.getArgument(0), null));
    when(jobRepository.findAll(any(Example.class), eq(pageable))).thenReturn(jobPage);
    // when
    Page<JobResponse> foundJobPage = jobService.findJobsByParams("Software Engineering", null, pageable);
    // then
    verify(jobRoleRepository).findByTitle("Software Engineering");
    verify(jobRepository).findAll(any(Example.class), eq(pageable));
    verify(locationRepository, never()).findFirstByCity(anyString());
    assertEquals(3, foundJobPage.getSize());
  }

  @Test
  @DisplayName("Find all job by city - Berlin")
  void shouldFilterJobsByCity_thenSuccess() {
    // given
    Pageable pageable = PageRequest.of(0, 20);
    Page<Job> jobPage = new PageImpl<>(Lists.newArrayList(job1));
    when(locationRepository.findFirstByCity("Berlin")).thenReturn(new Location("Berlin", "Germany"));
    when(jobRepository.findAll(any(Example.class), eq(pageable))).thenReturn(jobPage);
    // when
    Page<JobResponse> foundJobPage = jobService.findJobsByParams(null, "Berlin", pageable);
    // then
    verify(locationRepository).findFirstByCity("Berlin");
    verify(jobRepository).findAll(any(Example.class), eq(pageable));
    verify(jobRoleRepository, never()).findByTitle(anyString());
    assertEquals(1, foundJobPage.getSize());
  }

  @Test
  @DisplayName("Find job page of employer with id 1")
  void shouldFindAllEmployerJobs_thenSuccess() {
    // given
    Pageable pageable = PageRequest.of(0, 20);
    Page<Job> jobPage = new PageImpl<>(Lists.newArrayList(job1, job2, job3));
    when(jobRepository.findAllByPublisher_Id(1L, pageable)).thenReturn(jobPage);
    // when
    Page<JobResponse> jobList = jobService.fetchAllEmployerJobs(1L, pageable);
    //then
    verify(jobRepository).findAllByPublisher_Id(1L, pageable);
    assertEquals(jobPage.getSize(), jobList.getSize());
  }

  @Test
  @DisplayName("Find first job by the employer")
  void shouldFindEmployerFirstJob_thenSuccess() {
    when(jobRepository.findByIdAndPublisher_Id(1L, 1L)).thenReturn(Optional.of(job1));
    JobResponse job = jobService.fetchEmployerJob(1L, 1L);

    verify(jobRepository).findByIdAndPublisher_Id(1L, 1L);
    assertAll(
          () -> assertEquals(1L, job.getId()),
          () -> assertEquals("Senior Java Developer", job.getTitle())
    );
  }

  @Test
  @DisplayName("Throw exception when job not found")
  void shouldThrowException_whenJobNotFound() {
    assertThrows(ResourceNotFoundException.class,
          () -> jobService.fetchEmployerJob(1L, 5L),
          "An exception was not thrown");
    verify(jobRepository).findByIdAndPublisher_Id(anyLong(), anyLong());
  }

  @Test
  @DisplayName("Create a new job by the employer")
  void employerNewJobMustBeCreated_thenSuccess() {
    Long expectedJobId = 4L;
    // given
    LocationRequest location = new LocationRequest(
          Shorts.checkedCast(1L), "Toronto", "Canada");

    JobRequest newJob = JobRequest.builder()
          .title("Middle Front-end Developer (Angular, Firebase)")
          .jobRoleId((short) 1)
          .jobType("Full-time")
          .remote(false)
          .relocation(false)
          .visaSponsorship(false)
          .location(location)
          .skillIds(Lists.newArrayList(1L, 2L, 3L))
          .build();
    when(employerRepository.findById(1L)).thenReturn(Optional.of(employer1));
    when(jobRoleRepository.findById((short)1)).thenReturn(Optional.of(seRole));
    when(skillRepository.findAllById(Lists.newArrayList(1L, 2L, 3L)))
          .thenReturn(skills);
    when(jobRepository.save(any(Job.class))).thenReturn(Job.builder()
                .id(4L)
                .title("Middle Front-end Developer (Angular, Firebase)")
                .jobRole(new JobRole((short) 1, "Software Engineering", null))
                .jobType("Full-time")
                .relocation(false)
                .remote(false)
                .visaSponsorship(false)
                .location(new Location("Toronto", "Canada"))
                .build());
    // when
    JobResponse jobResponse = jobService.createEmployerJob(1L, newJob);
    //then
    verify(employerRepository).findById(1L);
    verify(jobRoleRepository).findById((short)1);
    verify(skillRepository).findAllById(Lists.newArrayList(1L, 2L, 3L));
    verify(jobRepository).save(any(Job.class));
    assertEquals(expectedJobId, jobResponse.getId());
    assertEquals("Middle Front-end Developer (Angular, Firebase)", jobResponse.getTitle());
    assertEquals("Software Engineering", jobResponse.getJobRoleTitle());
  }

  @Test
  @DisplayName("Update first job of the employer with id 1")
  void shouldUpdateEmployerFirstJob_thenSuccess() {
    Long employerId = 1L;
    Long jobId = 1L;
    String updatedTitle = "Java Consultant";
    when(jobRepository.findByIdAndPublisher_Id(jobId, employerId)).thenReturn(Optional.of(job1));
    when(jobRepository.save(any(Job.class)))
          .thenReturn(Job.builder()
                .id(1L)
                .title("Java Consultant")
                .jobRole(new JobRole((short) 1, "Software Engineering", null))
                .jobType("Full-time")
                .relocation(false)
                .remote(false)
                .visaSponsorship(false)
                .location(new Location("Toronto", "Canada"))
                .build());
    JobRequest jobRequest = JobRequest.builder().title(updatedTitle).build();
    JobResponse jobResponse = jobService.updateEmployerJob(employerId, jobId,  jobRequest);
    verify(jobRepository).findByIdAndPublisher_Id(jobId, employerId);
    verify(jobRepository).save(any(Job.class));
    assertEquals(jobId, jobResponse.getId());
    assertEquals(updatedTitle, jobResponse.getTitle());
  }

  @Test
  @DisplayName("Delete employer job with id 1")
  void shouldDeleteFirstJobByEmployer_thenSuccess() {
    // given
    when(jobRepository.existsByIdAndPublisher_Id(1L, 1L)).thenReturn(true);
    // when
    jobService.deleteEmployerJob(1L, 1L);
    // then
    verify(jobRepository).existsByIdAndPublisher_Id(1L, 1L);
    verify(jobRepository).deleteByIdAndPublisher_Id(1L, 1L);
  }
}

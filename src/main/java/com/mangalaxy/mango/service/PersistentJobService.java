package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.JobDto;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.JobRole;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.JobRepository;
import com.mangalaxy.mango.repository.JobRoleRepository;
import com.mangalaxy.mango.repository.LocationRepository;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of {@link JobService} interface.
 *
 * @see com.mangalaxy.mango.service.JobService
 */
@RequiredArgsConstructor
@Service
public class PersistentJobService implements JobService {

  private final JobRepository jobRepository;
  private final JobRoleRepository jobRoleRepository;
  private final EmployerRepository employerRepository;
  private final LocationRepository locationRepository;
  private final ModelMapper modelMapper;

  @Override
  @Transactional(readOnly = true)
  public Page<JobDto> selectJobsByParams(String jobRoleTitle, String city, Pageable pagination) {
    JobRole foundJobRole = null;
    Location foundLocation = null;
    if (jobRoleTitle != null) {
      foundJobRole = jobRoleRepository.findByTitle(jobRoleTitle);
    }
    if (city != null) {
      foundLocation = locationRepository.findByCity(city);
    }
    Job probe = Job.builder().jobRole(foundJobRole).location(foundLocation).build();
    Page<Job> foundJobs = jobRepository.findAll(Example.of(probe), pagination);
    return foundJobs.map(this::mapToDto);
  }

  @Override
  public Page<JobDto> getEmployerAllJobs(Long employerId, Pageable pagination) {
    Page<Job> jobs = jobRepository.findAllByPublisher_Id(employerId, pagination);
    return jobs.map(this::mapToDto);
  }

  @Override
  public JobDto getEmployerJob(Long employerId, Long jobId) {
    return jobRepository.findByIdAndPublisher_Id(jobId, employerId)
          .map(this::mapToDto)
          .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public JobDto createEmployerJob(Long employerId, JobRequest newJobInfo) {
    Employer employer = employerRepository.findById(employerId).orElseThrow(ResourceNotFoundException::new);
    Job job = modelMapper.map(newJobInfo, Job.class);
    employer.addJob(job);
    Job savedJob = jobRepository.save(job);
    return mapToDto(savedJob);
  }

  @Override
  public JobDto updateEmployerJob(Long employerId, Long jobId, JobRequest jobUpdate) {
    Job job = jobRepository.findByIdAndPublisher_Id(jobId, employerId).orElseThrow(ResourceNotFoundException::new);
    modelMapper.map(jobUpdate, job);
    Job updatedJob = jobRepository.save(job);
    return mapToDto(updatedJob);
  }

  @Override
  public void removeEmployerJob(Long employerId, Long jobId) {
    boolean isPresent = jobRepository.existsByIdAndPublisher_Id(jobId, employerId);
    if (isPresent) {
      jobRepository.deleteByIdAndPublisher_Id(jobId, employerId);
    }
  }

  // Helper methods for internal use cases.
  private JobDto mapToDto(final Job source) {
    return modelMapper.map(source, JobDto.class);
  }
}

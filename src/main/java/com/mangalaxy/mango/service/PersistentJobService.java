package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PersistentJobService implements JobService {

  private final JobRepository jobRepository;
  private final ModelMapper modelMapper;
  private final EmployerRepository employerRepository;

  @Override
  public Page<JobResponse> filterJobsByParams(String jobRole, String city, Pageable pageable) {
    Page<Job> allJobs = jobRepository.findAll(pageable);
    List<JobResponse> jobsByParams = allJobs.getContent().stream()
          .filter(job -> jobRole == null || job.getJobRole().getTitle().equalsIgnoreCase(jobRole))
          .filter(job -> city == null || job.getLocation().getCity().equalsIgnoreCase(city))
          .map(job -> modelMapper.map(job, JobResponse.class))
          .collect(Collectors.toList());
    return new PageImpl<>(jobsByParams, pageable, jobsByParams.size());
  }

  @Override
  public Page<JobResponse> fetchEmployerJobs(Long employerId, Pageable pageable) {
    Page<Job> jobs = jobRepository.findAllByPublisher_Id(employerId, pageable);
    List<JobResponse> employerJobs = jobs.stream().map(job -> modelMapper.map(job, JobResponse.class)).collect(Collectors.toList());
    return new PageImpl<>(employerJobs, pageable, employerJobs.size());
  }

  @Override
  public JobResponse findJobByEmployerAndId(Long jobId, Long employerId) {
    Job job = jobRepository.findByIdAndPublisher_Id(jobId, employerId);
    JobResponse jobResponse = modelMapper.map(job, JobResponse.class);
    return jobResponse;
  }

  @Override
  public JobResponse createNewJob(JobRequest jobRequest, Long employerId) {
    Employer employer = employerRepository.findById(employerId).orElseThrow(EmptyStackException::new);
    Job job = modelMapper.map(jobRequest, Job.class);
    job.setPublisher(employer);
    Job savedJob = jobRepository.save(job);
    return modelMapper.map(savedJob, JobResponse.class);
  }

  @Override
  public JobResponse updateJob(JobRequest jobRequest, Long employerId, Long jobId) {
    Employer employer = employerRepository.findById(employerId).orElseThrow(EmptyStackException::new);
    Job job = modelMapper.map(jobRequest, Job.class);
    job.setPublisher(employer);
    job.setId(jobId);
    Job updateJob = jobRepository.save(job);
    return modelMapper.map(updateJob, JobResponse.class);
  }

  @Override
  public void deleteJob(Long jobId, Long employerId) {
    Job job = jobRepository.findByIdAndPublisher_Id(jobId, employerId);
    jobRepository.delete(job);
  }
}


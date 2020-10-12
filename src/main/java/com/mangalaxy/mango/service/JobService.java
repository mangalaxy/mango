package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.JobDto;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Provides a basic interface for jobs service.
 */
public interface JobService {

  Page<JobDto> selectJobsByParams(String jobRole, String city, Pageable pageable);

  JobDto createEmployerJob(Long employerId, JobRequest jobRequest);

  Page<JobDto> fetchEmployerAllJobs(Long employerId, Pageable pageable);

  JobDto fetchEmployerJob(Long employerId, Long jobId);

  JobDto updateEmployerJob(Long employerId, Long jobId, JobRequest jobRequest);

  void deleteEmployerJob(Long employerId, Long jobId);

}

package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.JobDto;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Provides a basic interface for jobs service.
 */
public interface JobService {

  Page<JobDto> selectJobsByParams(String jobRole, String city, Pageable pagination);

  JobDto createEmployerJob(Long employerId, JobRequest newJobInfo);

  Page<JobDto> getEmployerAllJobs(Long employerId, Pageable pagination);

  JobDto getEmployerJob(Long employerId, Long jobId);

  JobDto updateEmployerJob(Long employerId, Long jobId, JobRequest jobUpdate);

  void removeEmployerJob(Long employerId, Long jobId);

}

package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Provides a basic interface for jobs service.
 */
public interface JobService {

  Page<JobResponse> selectJobsByParams(String jobRole, String city, Pageable pagination);

  JobResponse createEmployerJob(Long employerId, JobRequest newJobInfo);

  Page<JobResponse> getEmployerAllJobs(Long employerId, Pageable pagination);

  JobResponse getEmployerJob(Long employerId, Long jobId);

  JobResponse updateEmployerJob(Long employerId, Long jobId, JobRequest jobUpdate);

  void removeEmployerJob(Long employerId, Long jobId);

}

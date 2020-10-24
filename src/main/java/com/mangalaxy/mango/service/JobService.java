package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
  Page<JobResponse> selectJobsByParams(String jobRole, String city, Pageable pageable);
  JobResponse createEmployerJob(Long employerId, JobRequest jobRequest);
  Page<JobResponse> fetchEmployerAllJobs(Long employerId, Pageable pageable);
  JobResponse fetchEmployerJob(Long employerId, Long jobId);
  JobResponse updateEmployerJob(Long employerId, Long jobId, JobRequest jobRequest);
  void deleteEmployerJob(Long employerId, Long jobId);
}

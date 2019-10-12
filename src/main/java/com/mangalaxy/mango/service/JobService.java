package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
  Page<JobResponse> getJobsByParameters(String jobRole, String city, Pageable pageable);

  Page<JobResponse> getJobsForEmployer(Pageable pageable, Long emploterId);

  JobResponse findJobByEmployerAndId(Long jobId, Long employerId);

  JobResponse createNewJob(JobRequest jobRequest, Long employerId);

  JobResponse updateJob(JobRequest jobRequest, Long employerId, Long jobId);

  void deletJob(Long jobId, Long employerId);
}

package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.JobResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
  Page<JobResponse> getJobsByParameters(String jobRole, String city, Pageable pageable);
}

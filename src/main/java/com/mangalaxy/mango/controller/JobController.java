package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs")
public class JobController {
  private final JobService jobService;

  @GetMapping
  public ResponseEntity<Page<JobResponse>> getJobsByParams(
      @RequestParam(name = "jobRole", required = false) String jobRole,
      @RequestParam(name = "city", required = false) String city,
      Pageable pageable) {
    Page<JobResponse> jobs = jobService.getJobsByParameters(jobRole, city, pageable);
    return ResponseEntity.ok(jobs);
  }
}

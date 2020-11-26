package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class JobController {
  private final JobService jobService;

  @GetMapping("/api/v1/jobs")
  public ResponseEntity<Page<JobResponse>> getJobsByParams(@RequestParam(name = "jobRole", required = false) String jobRole,
                                                           @RequestParam(name = "city", required = false) String city,
                                                           Pageable pageable) {
    Page<JobResponse> jobs = jobService.findJobsByParams(jobRole, city, pageable);
    return ResponseEntity.ok(jobs);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @GetMapping("/api/v1/employers/{employerId}/jobs")
  public ResponseEntity<Page<JobResponse>> getEmployerPaginatedJobs(@PathVariable Long employerId, Pageable pageable) {
    Page<JobResponse> jobResponses = jobService.fetchAllEmployerJobs(employerId, pageable);
    return ResponseEntity.ok(jobResponses);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @GetMapping("/api/v1/employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<JobResponse> getSpecifiedEmployerJob(@PathVariable Long employerId, @PathVariable Long jobId) {
    JobResponse jobResponse = jobService.fetchEmployerJob(employerId, jobId);
    return ResponseEntity.ok(jobResponse);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @PostMapping("/api/v1/employers/{employerId}/jobs")
  public ResponseEntity<JobResponse> createEmployerJob(@PathVariable Long employerId, @RequestBody JobRequest jobRequest) {
    JobResponse createdJob = jobService.createEmployerJob(employerId, jobRequest);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(createdJob.getId())
          .toUri();
    return ResponseEntity.created(location).build();
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @PutMapping("/api/v1/employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<JobResponse> updateEmployerJob(@PathVariable Long employerId, @PathVariable Long jobId,
                                                       @RequestBody JobRequest jobRequest) {
    JobResponse jobResponse = jobService.updateEmployerJob(employerId, jobId, jobRequest);
    return ResponseEntity.ok(jobResponse);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @DeleteMapping("/api/v1/employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<Void> deleteEmployerJob(@PathVariable Long employerId, @PathVariable Long jobId) {
    jobService.deleteEmployerJob(employerId, jobId);
    return ResponseEntity.noContent().build();
  }

}

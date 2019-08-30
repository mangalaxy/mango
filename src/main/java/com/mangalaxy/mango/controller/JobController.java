package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class JobController {
  private final JobService jobService;

  @GetMapping("jobs")
  public ResponseEntity<Page<JobResponse>> getJobsByParams(
      @RequestParam(name = "jobRole", required = false) String jobRole,
      @RequestParam(name = "city", required = false) String city,
      Pageable pageable) {
    Page<JobResponse> jobs = jobService.getJobsByParameters(jobRole, city, pageable);
    return ResponseEntity.ok(jobs);
  }

  @GetMapping("employers/{employerId}/jobs")
  public ResponseEntity<Page<JobResponse>> getOpenEmployersJobs(@PathVariable Long employerId, Pageable pageable) {
    Page<JobResponse> jobs = jobService.getJobsForEmployer(pageable, employerId);
    return ResponseEntity.ok(jobs);
  }

  @GetMapping("employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<JobResponse> getJobForEmployer(@PathVariable Long employerId, @PathVariable Long jobId) {
    JobResponse jobResponse = jobService.findJobByEmployerAndId(jobId, employerId);
    return ResponseEntity.ok(jobResponse);
  }

  @PostMapping("employers/{id}/jobs")
  public ResponseEntity<JobResponse> createNewJob(@RequestBody JobRequest jobRequest, @PathVariable Long id) {
    JobResponse jobResponse = jobService.createNewJob(jobRequest, id);
    return new ResponseEntity<>(jobResponse, HttpStatus.CREATED);
  }

  @PutMapping("employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<JobResponse> updateJob(@RequestBody JobRequest jobRequest,
                                               @PathVariable Long employerId,
                                               @PathVariable Long jobId) {
    JobResponse jobResponse = jobService.updateJob(jobRequest, employerId, jobId);
    return ResponseEntity.ok(jobResponse);
  }

  @DeleteMapping("employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<Void> deleteJob(@PathVariable Long employerId, @PathVariable Long jobId) {
    jobService.deletJob(jobId, employerId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

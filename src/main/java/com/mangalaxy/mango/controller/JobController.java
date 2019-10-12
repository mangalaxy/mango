package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Api(value = "Job Data API", description = "List of methods that manage jobs")
public class JobController {

  private final JobService jobService;

  @GetMapping("jobs")
  @ApiOperation(value = "View a list of jobs by parameters")
  public ResponseEntity<Page<JobResponse>> getJobsByParams(
      @ApiParam(value = "JobRole from which job object will retrieve")
      @RequestParam(name = "jobRole", required = false) String jobRole,
      @ApiParam(value = "City from which job object will retrieve")
      @RequestParam(name = "city", required = false) String city,
      Pageable pageable) {
    Page<JobResponse> jobs = jobService.getJobsByParameters(jobRole, city, pageable);
    return ResponseEntity.ok(jobs);
  }

  @GetMapping("employers/{employerId}/jobs")
  @ApiOperation(value = "View a list of jobs by employer id")
  public ResponseEntity<Page<JobResponse>> getOpenEmployersJobs(
      @ApiParam(value = "Employer id from which list of job objects will retrieve", required = true)
      @PathVariable Long employerId, Pageable pageable) {
    Page<JobResponse> jobs = jobService.getJobsForEmployer(pageable, employerId);
    return ResponseEntity.ok(jobs);
  }

  @GetMapping("employers/{employerId}/jobs/{jobId}")
  @ApiOperation(value = "View job by employer and jobId")
  public ResponseEntity<JobResponse> getJobForEmployer(
      @ApiParam(value = "Employer id to check that job relevant for employer ")
      @PathVariable Long employerId,
      @ApiParam(value = "Job id from which job object will retrieve", required = true)
      @PathVariable Long jobId) {
    JobResponse jobResponse = jobService.findJobByEmployerAndId(jobId, employerId);
    return ResponseEntity.ok(jobResponse);
  }

  @PostMapping("employers/{id}/jobs")
  @ApiOperation(value = "Create new Job")
  public ResponseEntity<JobResponse> createNewJob(
      @ApiParam(value = "Job object store in database table", required = true)
      @RequestBody JobRequest jobRequest,
      @ApiParam(value = "Employer id for make relation job with employer", required = true)
      @PathVariable Long id) {
    JobResponse jobResponse = jobService.createNewJob(jobRequest, id);
    return new ResponseEntity<>(jobResponse, HttpStatus.CREATED);
  }

  @PutMapping("employers/{employerId}/jobs/{jobId}")
  @ApiOperation(value = "Update job")
  public ResponseEntity<JobResponse> updateJob(
      @ApiParam(value = "Update employer object", required = true)
      @RequestBody JobRequest jobRequest,
      @ApiParam(value = "Employer Id to update job object", required = true)
      @PathVariable Long employerId,
      @ApiParam(value = "Job id to update job object", required = true)
      @PathVariable Long jobId) {
    JobResponse jobResponse = jobService.updateJob(jobRequest, employerId, jobId);
    return ResponseEntity.ok(jobResponse);
  }

  @DeleteMapping("employers/{employerId}/jobs/{jobId}")
  @ApiOperation(value = "Delete job")
  public ResponseEntity<Void> deleteJob(
      @ApiParam(value = "Employer id from which job object will retrieve", required = true)
      @PathVariable Long employerId,
      @ApiParam(value = "Job id from which job object will retrieve", required = true)
      @PathVariable Long jobId) {
    jobService.deletJob(jobId, employerId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.JobDto;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Api(value = "JOBS REST API")
public class JobController {

  private final JobService jobService;

  @ApiOperation(value = "Get a paginated list of jobs filtered by parameters")
  @GetMapping("/jobs")
  public ResponseEntity<Page<JobDto>> getJobsByParams(@RequestParam(name = "jobRole", required = false) String jobRole,
                                                      @RequestParam(name = "city", required = false) String city,
                                                      Pageable pageable) {
    Page<JobDto> jobs = jobService.selectJobsByParams(jobRole, city, pageable);
    return ResponseEntity.ok(jobs);
  }

  @ApiOperation(value = "View a list of jobs by employer id")
  @GetMapping("/employers/{employerId}/jobs")
  public ResponseEntity<Page<JobDto>> getOpenEmployersJobs(@PathVariable Long employerId, Pageable pageable) {
    Page<JobDto> jobs = jobService.getEmployerAllJobs(employerId, pageable);
    return ResponseEntity.ok(jobs);
  }

  @ApiOperation(value = "Get specified job of specified employer")
  @GetMapping("/employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<JobDto> getJobForEmployer(@PathVariable Long employerId, @PathVariable Long jobId) {
    JobDto jobDto = jobService.getEmployerJob(employerId, jobId);
    return ResponseEntity.ok(jobDto);
  }

  @ApiOperation(value = "Create a ne job for specified employer")
  @PostMapping("/employers/{id}/jobs")
  public ResponseEntity<JobDto> createNewJob(@PathVariable Long id, @RequestBody JobRequest jobReq) {
    final JobDto jobDto = jobService.createEmployerJob(id, jobReq);
    return ResponseEntity.status(HttpStatus.CREATED).body(jobDto);
  }

  @ApiOperation(value = "Update specified job of specified employer")
  @PutMapping("/employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<JobDto> updateJob(@PathVariable Long employerId, @PathVariable Long jobId,
                                          @RequestBody JobRequest jobReq) {
    final JobDto jobDto = jobService.updateEmployerJob(employerId, jobId, jobReq);
    return ResponseEntity.ok(jobDto);
  }

  @ApiOperation(value = "Delete specified job of specified employer")
  @DeleteMapping("/employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<Void> deleteJob(@PathVariable Long employerId, @PathVariable Long jobId) {
    jobService.removeEmployerJob(employerId, jobId);
    return ResponseEntity.noContent().build();
  }

}

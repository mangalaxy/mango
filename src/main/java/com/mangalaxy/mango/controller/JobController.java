package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.JobDto;
import com.mangalaxy.mango.domain.dto.request.JobRequest;
import com.mangalaxy.mango.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    Page<JobDto> jobs = jobService.fetchEmployerAllJobs(employerId, pageable);
    return ResponseEntity.ok(jobs);
  }

  @ApiOperation(value = "Get specified job of specified employer")
  @GetMapping("/employers/{employerId}/jobs/{jobId}")
  public ResponseEntity<JobDto> getJobForEmployer(@PathVariable Long employerId, @PathVariable Long jobId) {
    JobDto jobDto = jobService.fetchEmployerJob(employerId, jobId);
    return ResponseEntity.ok(jobDto);
  }

  @PostMapping("/employers/{id}/jobs")
  public ResponseEntity<JobDto> createNewJob(@PathVariable Long id, @RequestBody JobRequest jobReq) {
    JobDto savedJob = jobService.createEmployerJob(id, jobReq);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(savedJob.getId())
          .toUri();
    return ResponseEntity.created(location).build();
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
    jobService.deleteEmployerJob(employerId, jobId);
    return ResponseEntity.noContent().build();
  }

}

package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.service.EmployerRelationshipService;
import com.mangalaxy.mango.service.EmployerService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/v1/employers")
@RequiredArgsConstructor
public class EmployerController {
  private final EmployerService employerService;
  private final EmployerRelationshipService employerRelationshipService;

  @GetMapping
  public ResponseEntity<Page<EmployerResponse>> getAllEmployers(Pageable pageable) {
    Page<EmployerResponse> response = employerService.fetchAllEmployers(pageable);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployerResponse> getEmployerById(
      @ApiParam(value = "Employer id from which employer object will retrieve", required = true)
      @PathVariable Long id) {
    EmployerResponse response = employerService.fetchEmployerById(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<EmployerResponse> createNewEmployer(@RequestBody EmployerRequest employerRequest) {
    EmployerResponse response = employerService.createNewEmployer(employerRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PreAuthorize("hasAuthority('EMPLOYER')")
  @PutMapping("/{id}")
  public ResponseEntity<EmployerResponse> updateEmployer(@PathVariable Long id, @RequestBody EmployerRequest employerRequest) {
    EmployerResponse response = employerService.updateEmployer(id, employerRequest);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasAuthority('EMPLOYER')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
    employerService.deleteEmployerById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PreAuthorize("hasAuthority('EMPLOYER')")
  @PutMapping("{employerId}/bookmarked/{talentId}")
  public ResponseEntity<EmployerResponse> matchTalentToEmployer(
      @ApiParam(value = "Employer id from which employer object will retrieve", required = true)
      @PathVariable Long employerId,
      @ApiParam(value = "Talent id from which employer object will retrieve", required = true)
      @PathVariable Long talentId,
      @ApiParam(value = "Flag defined to set association or delete the relationship")
      @RequestParam(name = "set") boolean set) {
    EmployerResponse response = employerRelationshipService.matchTalentToEmployer(employerId, talentId, set);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasAuthority('EMPLOYER')")
  @PutMapping("{employerId}/jobs/{jobId}/matched")
  public ResponseEntity<Page<TalentResponse>> getMatchedTalentsForJob(
      @PathVariable Long employerId,
      @PathVariable Long jobId,
      Pageable pageable) {
    Page<TalentResponse> response = employerRelationshipService.getMatchedTalentsForEmployerJob(employerId, jobId, pageable);
    return ResponseEntity.ok(response);
  }
}

package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.service.EmployerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/api/v1/employers")
@Api(value = "Employer Data API", description = "List of methods that manage employers")
public class EmployerController {

  private final EmployerService employerService;

  @GetMapping
  @ApiOperation(value = "View a list of employers by parameters")
  public ResponseEntity<Page<EmployerResponse>> findEmployersByParams(Pageable pageable) {
    Page<EmployerResponse> response = employerService.getEmployersByParams(pageable);
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  @ApiOperation(value = "Get employer by id")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Employer with given id not found")})
  public ResponseEntity<EmployerResponse> getEmployerById(
      @ApiParam(value = "Employer id from which employer object will retrieve", required = true)
      @PathVariable Long id) {
    EmployerResponse response = employerService.getEmployerById(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  @ApiOperation(value = "Create new employer")
  public ResponseEntity<EmployerResponse> createNewEmployer(
      @ApiParam(value = "Employer object store in database table", required = true)
      @RequestBody EmployerRequest employerRequest) {
    EmployerResponse response = employerService.createNewEmployer(employerRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  @ApiOperation(value = "Update employer")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Employer with given id not found")})
  public ResponseEntity<EmployerResponse> updateEmployer(
      @ApiParam(value = "Employer Id to update employer object", required = true)
      @PathVariable Long id,
      @ApiParam(value = "Update employer object", required = true)
      @RequestBody EmployerRequest employerRequest) {
    EmployerResponse response = employerService.updateEmployer(employerRequest, id);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("{id}")
  @ApiOperation(value = "Delete an employer")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Employer with given id not found")})
  public ResponseEntity<Void> deleteEnployer(
      @ApiParam(value = "Employer Id from which employee object will delete from database table", required = true)
      @PathVariable Long id) {
    employerService.deleteEmployer(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("{employerId}/bookmarked/{talentId}")
  @ApiOperation(value = "Math employer with talent")
  @ApiResponses(value = {
      @ApiResponse(code = 404, message = "Employer with given id not found"),
      @ApiResponse(code = 404, message = "Talent with given id not found")})
  public ResponseEntity<EmployerResponse> matchTalentToEmployer(
      @ApiParam(value = "Employer id from which employer object will retrieve", required = true)
      @PathVariable Long employerId,
      @ApiParam(value = "Talent id from which employer object will retrieve", required = true)
      @PathVariable Long talentId,
      @ApiParam(value = "Flag defined to set association or delete the relationship")
      @RequestParam(name = "set") boolean set) {
    EmployerResponse response = employerService.matchTalentToEmployer(employerId, talentId, set);
    return ResponseEntity.ok(response);
  }

  @PutMapping("{employerId}/jobs/{jobId}/matched")
  @ApiOperation(value = "Get list of matched talents for job")
  public ResponseEntity<Page<TalentResponse>> getMatchedTalentsForJob(
      @PathVariable Long employerId,
      @PathVariable Long jobId,
      Pageable pageable) {
    Page<TalentResponse> response = employerService.getMatchedTalentsForEmployerJob(employerId, jobId, pageable);
    return ResponseEntity.ok(response);
  }
}

package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.service.EmployerRelationshipService;
import com.mangalaxy.mango.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
public class EmployerController {
  private final EmployerService employerService;
  private final EmployerRelationshipService employerRelationshipService;

  @GetMapping("/api/v1/employers")
  public ResponseEntity<Page<EmployerResponse>> getAllEmployers(Pageable pageable) {
    Page<EmployerResponse> employersPage = employerService.fetchAllEmployers(pageable);
    return ResponseEntity.ok(employersPage);
  }

  @GetMapping("/api/v1/employers/{employerId}")
  public ResponseEntity<EmployerResponse> getSpecifiedEmployer(@PathVariable Long employerId) {
    EmployerResponse response = employerService.fetchEmployerById(employerId);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/api/v1/employers")
  public ResponseEntity<EmployerResponse> createNewEmployer(@Validated @RequestBody EmployerRequest employerRequest) {
    EmployerResponse createdEmployer = employerService.createNewEmployer(employerRequest);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(createdEmployer.getId())
          .toUri();
    return ResponseEntity.created(location).body(createdEmployer);
  }

  @PreAuthorize("hasAuthority('EMPLOYER')")
  @PutMapping("/api/v1/employers/{id}")
  public ResponseEntity<EmployerResponse> updateSpecifiedEmployer(@PathVariable Long id,
                                                                  @Validated @RequestBody EmployerRequest employerRequest) {
    EmployerResponse response = employerService.updateEmployerById(id, employerRequest);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasAuthority('EMPLOYER')")
  @DeleteMapping("/api/v1/employers/{employerId}")
  public ResponseEntity<Void> deleteSpecifiedEmployer(@PathVariable Long employerId) {
    employerService.deleteEmployerById(employerId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PreAuthorize("hasAuthority('EMPLOYER')")
  @PutMapping("/api/v1/employers/{employerId}/talents/{talentId}/bookmark")
  public ResponseEntity<Boolean> bookmarkTalentForEmployer(@PathVariable Long employerId,
                                                           @PathVariable Long talentId,
                                                           @RequestParam(name = "set") boolean set) {
    boolean done = employerRelationshipService.toggleTalentBookmark(employerId, talentId, set);
    return ResponseEntity.ok(done);
  }

  @PreAuthorize("hasAuthority('EMPLOYER')")
  @GetMapping("/api/v1/employers/{employerId}/talents/bookmarked")
  public ResponseEntity<Page<TalentResponse>> getAllBookmarkedTalents(@PathVariable Long employerId, Pageable pageable) {
    Page<TalentResponse> response = employerRelationshipService.fetchBookmarkedTalents(employerId, pageable);
    return ResponseEntity.ok(response);
  }
}

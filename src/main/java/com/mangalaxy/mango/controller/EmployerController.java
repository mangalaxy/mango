package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.EmployerUpdateRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.security.UserPrincipal;
import com.mangalaxy.mango.service.EmployerRelationshipService;
import com.mangalaxy.mango.service.EmployerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@Api(tags = "Employers API", produces = "application/json", consumes = "application/json")
@RequiredArgsConstructor
@RestController
public class EmployerController {
  private final EmployerService employerService;
  private final EmployerRelationshipService employerRelationshipService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/api/v1/employers")
  public ResponseEntity<Page<EmployerResponse>> getPaginatedEmployers(@RequestParam(name = "pageNumber", required = false,
                                                                            defaultValue = "0") int pageNumber,
                                                                      @RequestParam(name = "pageSize", required = false,
                                                                            defaultValue = "20") int pageSize) {
    Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
    Page<EmployerResponse> employersPage = employerService.fetchAllEmployers(pageRequest);
    return ResponseEntity.ok(employersPage);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @GetMapping("/api/v1/employers/{employerId}")
  public ResponseEntity<EmployerResponse> getSpecifiedEmployer(@PathVariable Long employerId) {
    EmployerResponse response = employerService.fetchEmployerById(employerId);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @GetMapping("/api/v1/employers/me")
  public ResponseEntity<Void> getCurrentEmployer(Authentication authentication) {
    if (authentication != null) {
      UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
      Long employerId = principal.getId();
      URI redirectUri = MvcUriComponentsBuilder.fromMethodName(this.getClass(), "getSpecifiedEmployer", employerId)
            .build()
            .toUri();
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setLocation(redirectUri);
      return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    } else {
      throw new IllegalStateException("UserDetails instance should not null");
    }

  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @PutMapping("/api/v1/employers/{id}")
  public ResponseEntity<EmployerResponse> updateSpecifiedEmployer(@PathVariable Long id,
                                                                  @RequestBody @Validated EmployerUpdateRequest employerRequest) {
    EmployerResponse response = employerService.updateEmployerById(id, employerRequest);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @PutMapping("/api/v1/employers/{employerId}/talents/{talentId}/bookmark")
  public ResponseEntity<Boolean> toggleTalentBookmark(@PathVariable Long employerId,
                                                      @PathVariable Long talentId,
                                                      @RequestParam(name = "set") boolean set) {
    boolean done = employerRelationshipService.toggleTalentBookmark(employerId, talentId, set);
    return ResponseEntity.ok(done);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @GetMapping("/api/v1/employers/{employerId}/talents/bookmarked")
  public ResponseEntity<Page<TalentResponse>> getTalentsBookmarkedBy(@PathVariable Long employerId,
                                                                     @RequestParam(name = "pageNumber", required = false,
                                                                           defaultValue = "0") int pageNumber,
                                                                     @RequestParam(name = "pageSize", required = false,
                                                                           defaultValue = "20") int pageSize) {
    Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
    Page<TalentResponse> response = employerRelationshipService.fetchBookmarkedTalents(employerId, pageRequest);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('EMPLOYER')")
  @DeleteMapping("/api/v1/employers/{employerId}")
  public ResponseEntity<Void> deleteSpecifiedEmployer(@PathVariable Long employerId) {
    employerService.deleteEmployerById(employerId);
    return ResponseEntity.noContent().build();
  }
}

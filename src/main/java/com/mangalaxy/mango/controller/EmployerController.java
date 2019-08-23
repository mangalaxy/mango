package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.service.EmployerService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employers")
public class EmployerController {
  private final EmployerService employerService;

  @GetMapping
  public ResponseEntity<Page<EmployerResponse>> findEmployersByParams(Pageable pageable) {
    Page<EmployerResponse> response = employerService.getEmployersByParams(pageable);
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  public ResponseEntity<EmployerResponse> getEmployerById(@PathVariable Long id) {
    EmployerResponse response = employerService.getEmployerById(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<EmployerResponse> createNewEmployer(@RequestBody EmployerRequest employerRequest) {
    EmployerResponse response = employerService.createNewEmployer(employerRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<EmployerResponse> updateEmployer(@RequestBody EmployerRequest employerRequest, @PathVariable Long id ) {
    EmployerResponse response = employerService.updateEmployer(employerRequest, id);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteEnployer(@PathVariable Long id) {
    employerService.deleteEmployer(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}

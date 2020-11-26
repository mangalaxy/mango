package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.EmployerUpdateRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployerService {
  Page<EmployerResponse> fetchAllEmployers(Pageable pageable);
  EmployerResponse fetchEmployerById(Long id);
  boolean isEmailFree(String email);
  EmployerResponse createNewEmployer(EmployerSignUpRequest employerRequest);
  EmployerResponse updateEmployerById(Long id, EmployerUpdateRequest employerRequest);
  void deleteEmployerById(Long id);
}

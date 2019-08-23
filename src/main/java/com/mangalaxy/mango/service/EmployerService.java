package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployerService {
  Page<EmployerResponse> getEmployersByParams(Pageable pageable);

  EmployerResponse getEmployerById(Long id);

  EmployerResponse createNewEmployer(EmployerRequest request);

  EmployerResponse updateEmployer(EmployerRequest request, Long id);

  void deleteEmployer(Long id);
}

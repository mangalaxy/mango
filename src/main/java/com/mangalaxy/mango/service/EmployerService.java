package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployerService {

  Page<EmployerResponse> fetchAllEmployers(Pageable pageable);

  EmployerResponse fetchEmployerById(Long id);

  EmployerResponse createNewEmployer(EmployerRequest employer);

  EmployerResponse updateEmployer(Long id, EmployerRequest employer);

  void deleteEmployerById(Long id);

}

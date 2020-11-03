package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.exception.EmployerNotFoundException;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EmployerServiceImpl implements EmployerService {
  private final EmployerRepository employerRepository;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  @Override
  public Page<EmployerResponse> fetchAllEmployers(Pageable pageable) {
    Page<Employer> employerPage = employerRepository.findAll(pageable);
    return employerPage.map(employer ->
          modelMapper.map(employer, EmployerResponse.EmployerResponseBuilder.class).build());
  }

  @Transactional(readOnly = true)
  @Override
  public EmployerResponse fetchEmployerById(Long id) {
    Employer employer = findEmployer(id);
    return modelMapper.map(employer, EmployerResponse.EmployerResponseBuilder.class).build();
  }

  @Transactional
  @Override
  public EmployerResponse createNewEmployer(EmployerRequest employer) {
    Employer employerEntity = modelMapper.map(employer, Employer.class);
    Employer savedEmployer = employerRepository.save(employerEntity);
    return modelMapper.map(savedEmployer, EmployerResponse.EmployerResponseBuilder.class).build();
  }

  @Transactional
  @Override
  public EmployerResponse updateEmployer(Long id, EmployerRequest employer) {
    Employer foundEmployer = findEmployer(id);
    modelMapper.map(employer, foundEmployer);
    Employer updatedEmployer = employerRepository.save(foundEmployer);
    return modelMapper.map(updatedEmployer, EmployerResponse.EmployerResponseBuilder.class).build();
  }

  @Transactional
  @Override
  public void deleteEmployerById(Long id) {
    Employer employer = findEmployer(id);
    employerRepository.delete(employer);
  }

  // Shortcut method to find employer
  private Employer findEmployer(Long id) {
    return employerRepository.findById(id).orElseThrow(EmployerNotFoundException::new);
  }
}

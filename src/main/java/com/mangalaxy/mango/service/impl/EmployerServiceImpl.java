package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.Role;
import com.mangalaxy.mango.domain.dto.request.EmployerSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.EmployerUpdateRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.repository.CompanyRepository;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.service.EmployerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployerServiceImpl implements EmployerService {
  private final EmployerRepository employerRepository;
  private final CompanyRepository companyRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  @Override
  public Page<EmployerResponse> fetchAllEmployers(Pageable pageable) {
    Page<Employer> employerPage = employerRepository.findAll(pageable);
    return employerPage.map(this::mapToDto);
  }

  @Transactional(readOnly = true)
  @Override
  public EmployerResponse fetchEmployerById(Long id) {
    Employer employer = findEmployer(id);
    log.info("Fetched employer instance with id={} and details: {}", id, employer);
    return mapToDto(employer);
  }

  @Override
  public boolean isEmailFree(String email) {
    return !employerRepository.existsByEmail(email);
  }

  @Transactional
  @Override
  public EmployerResponse createNewEmployer(EmployerSignUpRequest employerRequest) {
    Employer employer = modelMapper.map(employerRequest, Employer.class);
    String rawPassword = employerRequest.getPassword();
    employer.setPassword(passwordEncoder.encode(rawPassword));
    employer.setRole(Role.ROLE_EMPLOYER);
    String companyName = employer.getCompany().getName();
    companyRepository.findByNameIgnoreCase(companyName).ifPresent(employer::setCompany);
    employer = employerRepository.save(employer);
    log.info("The employer instance was saved as {}", employer);
    return mapToDto(employer);
  }

  @Transactional
  @Override
  public EmployerResponse updateEmployerById(Long id, EmployerUpdateRequest employerRequest) {
    Employer employer = findEmployer(id);
    modelMapper.map(employerRequest, employer);
    Employer updatedEmployer = employerRepository.save(employer);
    log.info("The employer instance was updated as: {}", updatedEmployer);
    return mapToDto(updatedEmployer);
  }

  @Transactional
  @Override
  public void deleteEmployerById(Long id) {
    Employer employer = findEmployer(id);
    employerRepository.delete(employer);
    log.info("The employer with ID {} was deleted", id);
  }

  // Shortcut method to find employer
  private Employer findEmployer(Long id) {
    return employerRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("employer", "id", id));
  }

  private EmployerResponse mapToDto(Employer employer) {
    return modelMapper.map(employer, EmployerResponse.EmployerResponseBuilder.class).build();
  }
}

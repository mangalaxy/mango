package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.CompanyRequest;
import com.mangalaxy.mango.domain.dto.response.CompanyResponse;
import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.repository.CompanyRepository;
import com.mangalaxy.mango.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
  private final CompanyRepository companyRepository;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  @Override
  public CompanyResponse fetchCompanyById(Long id) {
    Company company = findCompany(id);
    log.info("Fetched company instance with id={} and details: {}", id, company);
    return mapToDto(company);
  }

  @Transactional
  @Override
  public CompanyResponse createNewCompany(CompanyRequest companyRequest) {
    Company company = modelMapper.map(companyRequest, Company.class);
    company = companyRepository.save(company);
    log.info("The company instance was saved as {}", company);
    return mapToDto(company);
  }

  @Transactional
  @Override
  public CompanyResponse updateCompanyById(Long id, CompanyRequest companyRequest) {
    Company company = findCompany(id);
    modelMapper.map(companyRequest, company);
    Company updatedCompany = companyRepository.save(company);
    log.info("The company instance was updated as: {}", updatedCompany);
    return mapToDto(updatedCompany);
  }

  @Transactional
  @Override
  public void deleteCompanyById(Long id) {
    Company company = findCompany(id);
    companyRepository.delete(company);
    log.info("The company was deleted by id={}", id);
  }

  private Company findCompany(Long id) {
    return companyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }

  private CompanyResponse mapToDto(Company company) {
    return modelMapper.map(company, CompanyResponse.CompanyResponseBuilder.class).build();
  }
}
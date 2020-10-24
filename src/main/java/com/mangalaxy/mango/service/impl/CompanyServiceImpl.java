package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.CompanyRequest;
import com.mangalaxy.mango.domain.dto.response.CompanyResponse;
import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.repository.CompanyRepository;
import com.mangalaxy.mango.service.CompanyService;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: Define implementations for service methods
@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements CompanyService {
  private final CompanyRepository companyRepository;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  @Override
  public CompanyResponse fetchCompanyById(Long id) {
    Company company = companyRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return modelMapper.map(company, CompanyResponse.class);
  }

  @Transactional
  @Override
  public CompanyResponse createNewCompany(CompanyRequest companyRequest) {
    return null;
  }

  @Transactional
  @Override
  public CompanyResponse updateCompanyById(Long id, CompanyRequest companyRequest) {
    return null;
  }

  @Transactional
  @Override
  public void deleteCompanyById(Long id) {

  }
}

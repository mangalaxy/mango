package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.CompanyRequest;
import com.mangalaxy.mango.domain.dto.response.CompanyResponse;

public interface CompanyService {
  CompanyResponse fetchCompanyById(Long id);
  CompanyResponse fetchCompanyByEmployerId(Long employerId);
  CompanyResponse fetchCompanyByName(String name);
  CompanyResponse createNewCompany(CompanyRequest companyRequest);
  CompanyResponse updateCompanyById(Long id, CompanyRequest companyRequest);
  void deleteCompanyById(Long id);
}

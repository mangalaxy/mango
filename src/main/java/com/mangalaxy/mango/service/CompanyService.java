package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.CompanyRequest;
import com.mangalaxy.mango.domain.dto.response.CompanyResponse;

public interface CompanyService {
  CompanyResponse fetchCompanyById(Long id);
  CompanyResponse createNewCompany(CompanyRequest companyRequest);
  CompanyResponse updateCompanyById(Long id, CompanyRequest companyRequest);
  void deleteCompanyById(Long id);
}

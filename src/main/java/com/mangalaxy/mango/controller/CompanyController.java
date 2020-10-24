package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.CompanyRequest;
import com.mangalaxy.mango.domain.dto.response.CompanyResponse;
import com.mangalaxy.mango.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class CompanyController {
  private final CompanyService companyService;

  @GetMapping("/api/v1/companies/{companyId}")
  public ResponseEntity<CompanyResponse> getSpecifiedCompany(@PathVariable Long companyId) {
    CompanyResponse company = companyService.fetchCompanyById(companyId);
    return ResponseEntity.ok(company);
  }

  @PostMapping("/api/v1/companies")
  public ResponseEntity<CompanyResponse> createNewCompany(@RequestBody CompanyRequest companyRequest) {
    CompanyResponse createdCompany = companyService.createNewCompany(companyRequest);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(createdCompany.getId())
          .toUri();
    return ResponseEntity.created(location).body(createdCompany);
  }

  @PutMapping("/api/v1/companies/{companyId}")
  public ResponseEntity<CompanyResponse> updateSpecifiedCompany(@PathVariable Long companyId,
                                                                @RequestBody CompanyRequest companyRequest) {
    CompanyResponse companyResponse = companyService.updateCompanyById(companyId, companyRequest);
    return ResponseEntity.ok(companyResponse);
  }

  @DeleteMapping("/api/v1/companies/{companyId}")
  public ResponseEntity<Void> deleteSpecifiedCompany(@PathVariable Long companyId) {
    companyService.deleteCompanyById(companyId);
    return ResponseEntity.noContent().build();
  }
}

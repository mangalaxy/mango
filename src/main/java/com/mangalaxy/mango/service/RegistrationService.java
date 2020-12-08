package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.TalentSignUpRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;

public interface RegistrationService {
  Long register(EmployerSignUpRequest employerRequest);
  Long register(TalentSignUpRequest talentRequest);
  ApiResponse confirmRegistration(String validationToken);
}

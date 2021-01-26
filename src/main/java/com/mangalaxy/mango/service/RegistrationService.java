package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.TalentSignUpRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;

public interface RegistrationService {
  Long enroll(EmployerSignUpRequest employerRequest);
  Long enroll(TalentSignUpRequest talentRequest);
  ApiResponse confirmEmailToken(String emailToken);
}

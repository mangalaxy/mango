package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.EmployerSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.TalentSignUpRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.exception.BadRequestException;
import com.mangalaxy.mango.service.EmployerService;
import com.mangalaxy.mango.service.RegistrationService;
import com.mangalaxy.mango.service.TalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultRegistrationService implements RegistrationService {
  private final EmployerService employerService;
  private final TalentService talentService;

  @Override
  public Long register(EmployerSignUpRequest employerRequest) {
    if (employerService.isEmailFree(employerRequest.getEmail())) {
      return employerService.createNewEmployer(employerRequest).getId();
    } else {
      throw new BadRequestException("User with this email already exists");
    }
  }

  @Override
  public Long register(TalentSignUpRequest talentRequest) {
    if (talentService.isEmailFree(talentRequest.getEmail())) {
      return talentService.createNewTalent(talentRequest).getId();
    } else {
      throw new BadRequestException("User with this email already exists");
    }
  }

  @Override
  public ApiResponse confirmRegistration(String validationToken) {
    // TODO: Implement application logic in different branch
    return new ApiResponse(true, "Email confirmed successfully");
  }

}

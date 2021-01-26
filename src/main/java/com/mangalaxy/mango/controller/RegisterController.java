package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.EmployerSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.TalentSignUpRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.service.RegistrationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@Api(tags = "Sign Up API", produces = "application/json", consumes = "application/json")
@RequiredArgsConstructor
@RestController
public class RegisterController {
  private final RegistrationService registrationService;

  @PostMapping("/api/v1/auth/employer/register")
  public ResponseEntity<ApiResponse> registerEmployer(@RequestBody @Validated EmployerSignUpRequest employerRequest) {
    Long assignedId = registrationService.enroll(employerRequest);
    URI location = MvcUriComponentsBuilder
          .fromMethodName(EmployerController.class, "getSpecifiedEmployer", assignedId)
          .build()
          .toUri();
    ApiResponse successResponse = new ApiResponse(true, "Employer registered successfully");
    return ResponseEntity.created(location).body(successResponse);
  }

  @PostMapping("/api/v1/auth/talent/register")
  public ResponseEntity<ApiResponse> registerTalent(@RequestBody @Validated TalentSignUpRequest talentRequest) {
    Long assignedId = registrationService.enroll(talentRequest);
    URI location = MvcUriComponentsBuilder
          .fromMethodName(TalentController.class, "getSpecifiedTalent", assignedId)
          .build()
          .toUri();
    ApiResponse successResponse = new ApiResponse(true, "Talent registered successfully");
    return ResponseEntity.created(location).body(successResponse);
  }

  @PutMapping("/api/v1/auth/confirm/email")
  public ResponseEntity<ApiResponse> confirmEmailByToken(@RequestParam("token") String activationToken) {
    ApiResponse confirmedResponse = registrationService.confirmEmailToken(activationToken);
    return ResponseEntity.ok(confirmedResponse);
  }
}

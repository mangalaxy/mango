package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Value
public class TalentSignUpRequest {

  @NotBlank
  @Size(min = 3, max = 60)
  String fullName;

  @Email
  @Size(max = 45)
  String email;

  @NotBlank
  @Size(min = 8, max = 64)
  String password;

  @NotNull
  LocationRequest location;

  @JsonCreator
  public TalentSignUpRequest(@JsonProperty("fullName") String fullName,
                             @JsonProperty("email") String email,
                             @JsonProperty("password") String password,
                             @JsonProperty("location") LocationRequest location) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.location = location;
  }
}

package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Value
public class EmployerSignUpRequest {

  @NotBlank
  @Size(min = 3, max = 60)
  String fullName;

  @NotBlank
  @Email
  @Size(max = 45)
  String email;

  @NotBlank
  @Size(min = 8, max = 64)
  String password;

  @Size(max = 18)
  String phone;

  @NotBlank
  @Size(min = 3, max = 45)
  String companyName;

  @Size(max = 45)
  String jobTitle;

  @URL
  @Size(max = 255)
  String photoUrl;

  @NotNull
  LocationRequest location;

  @JsonCreator
  public EmployerSignUpRequest(
        @JsonProperty("fullName") String fullName,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("phone") String phone,
        @JsonProperty("companyName") String companyName,
        @JsonProperty("jobTitle") String jobTitle,
        @JsonProperty("photoUrl") String photoUrl,
        @JsonProperty("location") LocationRequest location) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.companyName = companyName;
    this.jobTitle = jobTitle;
    this.photoUrl = photoUrl;
    this.location = location;
  }
}

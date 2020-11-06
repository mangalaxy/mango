package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Value
public class EmployerRequest {

  @NotBlank
  @Size(min = 2, max = 60)
  String fullName;

  @Email
  @Size(max = 45)
  String email;

  @NotBlank
  @Size(min = 8)
  String password;

  @Size(max = 18)
  String phoneNumber;

  @NotBlank
  @Size(min = 3, max = 45)
  String companyName;

  @Size(max = 45)
  String jobTitle;

  @URL
  @Size(max = 255)
  String photoUrl;

  LocationRequest location;

  @JsonCreator
  public EmployerRequest(
        @JsonProperty("fullName") String fullName,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("phoneNumber") String phoneNumber,
        @JsonProperty("companyName") String companyName,
        @JsonProperty("jobTitle") String jobTitle,
        @JsonProperty("photoUrl") String photoUrl,
        @JsonProperty("location") LocationRequest location) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.companyName = companyName;
    this.jobTitle = jobTitle;
    this.photoUrl = photoUrl;
    this.location = location;
  }
}

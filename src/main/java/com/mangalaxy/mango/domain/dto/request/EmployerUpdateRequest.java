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
public class EmployerUpdateRequest {

  @NotBlank
  @Size(min = 3, max = 60)
  String fullName;

  @NotBlank
  @Email
  @Size(max = 45)
  String email;

  @Size(max = 18)
  String phone;

  @Size(max = 45)
  String jobTitle;

  @URL
  @Size(max = 255)
  String photoUrl;

  @NotNull
  LocationRequest location;

  @JsonCreator
  public EmployerUpdateRequest create(
        @JsonProperty("fullName") String fullName,
        @JsonProperty("email") String email,
        @JsonProperty("phone") String phone,
        @JsonProperty("jobTitle") String jobTitle,
        @JsonProperty("photoUrl") String photoUrl,
        @JsonProperty("location") LocationRequest location) {
    return new EmployerUpdateRequest(
          fullName,
          email,
          phone,
          jobTitle,
          photoUrl,
          location);
  }
}

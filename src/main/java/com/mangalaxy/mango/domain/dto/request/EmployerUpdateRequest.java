package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@ToString(doNotUseGetters = true)
public class EmployerUpdateRequest {

  @NotBlank
  @Size(min = 2, max = 60)
  String fullName;

  @Email
  @Size(max = 45)
  String email;

  @Size(max = 18)
  String phoneNumber;

  @Size(max = 45)
  String jobTitle;

  @URL
  @Size(max = 255)
  String photoUrl;

  LocationRequest location;

  @JsonCreator
  public EmployerUpdateRequest create(
        @JsonProperty("fullName") String fullName,
        @JsonProperty("email") String email,
        @JsonProperty("phoneNumber") String phoneNumber,
        @JsonProperty("jobTitle") String jobTitle,
        @JsonProperty("photoUrl") String photoUrl,
        @JsonProperty("location") LocationRequest location) {
    return new EmployerUpdateRequest(
          fullName,
          email,
          phoneNumber,
          jobTitle,
          photoUrl,
          location);
  }
}

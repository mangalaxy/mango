package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// TODO: Remove builder for the class, leverage static factory method for tests.
@Builder
@Value
@ToString(doNotUseGetters = true)
public class TalentRequest {

  @NotBlank
  @Size(min = 2, max = 60)
  String fullName;

  @Email
  @Size(max = 45)
  String email;

  @NotBlank
  @Size(min = 8)
  String password;

  LocationRequest location;

  @JsonCreator
  public TalentRequest(
        @JsonProperty("fullName") String fullName,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("location") LocationRequest location) {
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.location = location;
  }
}

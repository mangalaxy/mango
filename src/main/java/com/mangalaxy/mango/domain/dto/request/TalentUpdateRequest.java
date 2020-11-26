package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@ToString(doNotUseGetters = true)
public class TalentUpdateRequest {

  @NotBlank
  @Size(min = 2, max = 60)
  String fullName;

  @Email
  @Size(max = 45)
  String email;

  LocationRequest location;

  @JsonCreator
  public TalentUpdateRequest create(
        @JsonProperty("fullName") String fullName,
        @JsonProperty("email") String email,
        @JsonProperty("location") LocationRequest location) {
    return new TalentUpdateRequest(fullName, email, location);
  }
}

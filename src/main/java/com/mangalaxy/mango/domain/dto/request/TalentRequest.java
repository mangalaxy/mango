package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TalentRequest {
  private final Long id;
  private final String fullName;
  private final String email;
  private final String password;
  private final LocationRequest location;

  @JsonCreator
  public TalentRequest(@JsonProperty("id") Long id, @JsonProperty("fullName") String fullName,
                       @JsonProperty("email") String email, @JsonProperty("password") String password,
                       @JsonProperty("location") LocationRequest location) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.password = password;
    this.location = location;
  }
}

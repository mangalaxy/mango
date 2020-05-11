package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployerRequest {

  private final String fullName;
  private final String email;
  private final String phoneNumber;
  private final String companyName;
  private final String jobTitle;
  private final String photoUrl;
  private final LocationRequest location;

  @JsonCreator
  public EmployerRequest(@JsonProperty("fullName") String fullName,
                         @JsonProperty("email") String email,
                         @JsonProperty("phoneNumber") String phoneNumber,
                         @JsonProperty("companyName") String companyName,
                         @JsonProperty("jobTitle") String jobTitle,
                         @JsonProperty("photoUrl") String photoUrl,
                         @JsonProperty("location") LocationRequest location) {
    this.fullName = fullName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.companyName = companyName;
    this.jobTitle = jobTitle;
    this.photoUrl = photoUrl;
    this.location = location;
  }
}

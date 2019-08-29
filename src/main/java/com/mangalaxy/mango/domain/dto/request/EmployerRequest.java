package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.Location;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class EmployerRequest {
  private final Long id;
  private final String password;
  private final String fullName;
  private final String workEmail;
  private final String phoneNumber;
  private final Company company;
  private final String jobTitle;
  private final String photoUrl;
  private final Location location;

  @JsonCreator
  public EmployerRequest(@JsonProperty("id") Long id, @JsonProperty("password") String password,
                         @JsonProperty("fullName") String fullName,  @JsonProperty("workEmail") String workEmail,
                         @JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("company") Company company,
                         @JsonProperty("jobTitle") String jobTitle, @JsonProperty("photoUrl") String photoUrl,
                         @JsonProperty("location") Location location) {
    this.id = id;
    this.password = password;
    this.fullName = fullName;
    this.workEmail = workEmail;
    this.phoneNumber = phoneNumber;
    this.company = company;
    this.jobTitle = jobTitle;
    this.photoUrl = photoUrl;
    this.location = location;
  }
}

package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@Value
public class JobRequest {

  @NotBlank
  String title;

  @NotBlank
  String jobRoleTitle;

  @NotBlank
  String jobType;

  @NotNull
  Boolean remote;

  @NotNull
  Boolean relocation;

  @NotNull
  Boolean visaSponsorship;

  @NotBlank
  String experienceRequired;

  String description;

  @NotNull
  LocationRequest location;
  Set<SkillRequest> skills;

  @JsonCreator
  public JobRequest(
        @JsonProperty("title") String title,
        @JsonProperty("jobRole") String jobRoleTitle,
        @JsonProperty("jobType") String jobType,
        @JsonProperty("remote") Boolean remote,
        @JsonProperty("relocation") Boolean relocation,
        @JsonProperty("visaSponsorship") Boolean visaSponsorship,
        @JsonProperty("experienceRequired") String experienceRequired,
        @JsonProperty("description") String description,
        @JsonProperty("location") LocationRequest location,
        @JsonProperty("skills") Set<SkillRequest> skills) {
    this.title = title;
    this.jobType = jobType;
    this.remote = remote;
    this.relocation = relocation;
    this.visaSponsorship = visaSponsorship;
    this.experienceRequired = experienceRequired;
    this.description = description;
    this.location = location;
    this.skills = skills;
    this.jobRoleTitle = jobRoleTitle;
  }

}

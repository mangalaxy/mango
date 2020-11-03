package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class JobRequest {
  String title;
  String jobRole;
  String jobType;
  Boolean remote;
  Boolean relocation;
  Boolean visaSponsorship;
  String experienceRequired;
  String description;
  LocationRequest location;
  Set<SkillRequest> skills;

  @JsonCreator
  public JobRequest(@JsonProperty("title") String title,
                    @JsonProperty("jobRole") String jobRole,
                    @JsonProperty("jobType") String jobType,
                    @JsonProperty("remote") Boolean remote,
                    @JsonProperty("relocation") Boolean isRelocate,
                    @JsonProperty("visaSponsorship") Boolean isVisaSponsorship,
                    @JsonProperty("experienceRequired") String experienceRequired,
                    @JsonProperty("description") String description,
                    @JsonProperty("location") LocationRequest location,
                    @JsonProperty("skills") Set<SkillRequest> skills) {
    this.title = title;
    this.jobType = jobType;
    this.remote = remote;
    this.relocation = isRelocate;
    this.visaSponsorship = isVisaSponsorship;
    this.experienceRequired = experienceRequired;
    this.description = description;
    this.location = location;
    this.skills = skills;
    this.jobRole = jobRole;
  }

}

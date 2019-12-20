package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class JobRequest {
  private final String title;
  private final String jobRole;
  private final String employmentType;
  private final Boolean remote;
  private final Boolean relocation;
  private final Boolean visaSponsorship;
  private final String requiredExperience;
  private final LocationRequest location;
  private final Set<SkillRequest> skills;

  @Builder
  @JsonCreator
  public JobRequest(@JsonProperty("title") String title,
                    @JsonProperty("jobRole") String jobRole,
                    @JsonProperty("employmentType") String employmentType,
                    @JsonProperty("isRemote") Boolean isRemote,
                    @JsonProperty("isRelocate") Boolean isRelocate,
                    @JsonProperty("isVisaSponsorship") Boolean isVisaSponsorship,
                    @JsonProperty("xpRange") String xpRange,
                    @JsonProperty("location") LocationRequest location,
                    @JsonProperty("skills") Set<SkillRequest> skills) {
    this.title = title;
    this.employmentType = employmentType;
    this.remote = isRemote;
    this.relocation = isRelocate;
    this.visaSponsorship = isVisaSponsorship;
    this.requiredExperience = xpRange;
    this.location = location;
    this.skills = skills;
    this.jobRole = jobRole;
  }

}

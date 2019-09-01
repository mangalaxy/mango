package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.dto.response.SkillResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class JobRequest {
  private final Long id;
  private final String title;
  private final String employmentType;
  private final Boolean isRemote;
  private final Boolean isRelocate;
  private final Boolean isVisaSponsorship;
  private final String xpRange;
  private final LocationRequest location;
  private final Set<SkillRequest> skills;
  private final String jobRole;

  @JsonCreator
  public JobRequest(@JsonProperty("id") Long id, @JsonProperty("title") String title,
                    @JsonProperty("employmentType") String employmentType, @JsonProperty("isRemote") Boolean isRemote,
                    @JsonProperty("isRelocate") Boolean isRelocate, @JsonProperty("isVisaSponsorship") Boolean isVisaSponsorship,
                    @JsonProperty("xpRange") String xpRange, @JsonProperty("location") LocationRequest location,
                    @JsonProperty("skills") Set<SkillRequest> skills, @JsonProperty("jobRole") String jobRole) {
    this.id = id;
    this.title = title;
    this.employmentType = employmentType;
    this.isRemote = isRemote;
    this.isRelocate = isRelocate;
    this.isVisaSponsorship = isVisaSponsorship;
    this.xpRange = xpRange;
    this.location = location;
    this.skills = skills;
    this.jobRole = jobRole;
  }
}

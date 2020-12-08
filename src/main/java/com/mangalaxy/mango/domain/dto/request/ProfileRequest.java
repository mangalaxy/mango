package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mangalaxy.mango.domain.CandidateStatus;
import com.mangalaxy.mango.domain.entity.Education;
import com.mangalaxy.mango.domain.entity.Experience;
import com.mangalaxy.mango.domain.entity.Language;
import com.mangalaxy.mango.domain.entity.Salary;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class ProfileRequest {
  private final Long id;
  private final TalentSignUpRequest owner;
  private final String photoUrl;
  private final String jobRole;
  private final Set<SkillRequest> skills;
  private final CandidateStatus status;
  private final LocationRequest preferredLocation;
  private final Salary preferredSalary;
  private final Set<String> expectations;
  private final List<String> preferredCompanyType;
  private final List<Experience> experiences;
  private final List<Education> educations;
  private final List<Language> preferredLanguages;

  @JsonCreator
  public ProfileRequest(@JsonProperty("id") Long id,
                        @JsonProperty("owner") TalentSignUpRequest owner,
                        @JsonProperty("photoUrl") String photoUrl,
                        @JsonProperty("jobRole") String jobRole,
                        @JsonProperty("skills") Set<SkillRequest> skills,
                        @JsonProperty("status") CandidateStatus status,
                        @JsonProperty("preferredLocation") LocationRequest preferredLocation,
                        @JsonProperty("preferredSalary")  Salary preferredSalary,
                        @JsonProperty("expectations") Set<String> expectations,
                        @JsonProperty("preferredCompanyType") List<String> preferredCompanyType,
                        @JsonProperty("experiences") List<Experience> experiences,
                        @JsonProperty("educations") List<Education> educations,
                        @JsonProperty("preferredLanguages") List<Language> preferredLanguages) {
    this.id = id;
    this.owner = owner;
    this.photoUrl = photoUrl;
    this.jobRole = jobRole;
    this.skills = skills;
    this.status = status;
    this.preferredLocation = preferredLocation;
    this.preferredSalary = preferredSalary;
    this.expectations = expectations;
    this.preferredCompanyType = preferredCompanyType;
    this.experiences = experiences;
    this.educations = educations;
    this.preferredLanguages = preferredLanguages;
  }
}

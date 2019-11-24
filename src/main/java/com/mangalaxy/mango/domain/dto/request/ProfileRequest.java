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
  private final TalentRequest owner;
  private final String photoUrl;
  private final String selectedJobRole;
  private final Set<SkillRequest> skills;
  private final CandidateStatus status;
  private final LocationRequest preferredLocation;
  private final Salary preferredSalary;
  private final Set<String> expectations;
  private final List<String> preferredCompanyType;
  private final List<Experience> experiences;
  private final List<Education> educations;
  private final List<Language> preferredLang;

  @JsonCreator
  public ProfileRequest(@JsonProperty("id") Long id, @JsonProperty("owner") TalentRequest owner,
                        @JsonProperty("photoUrl") String photoUrl, @JsonProperty("selectedJobRole") String selectedJobRole,
                        @JsonProperty("skills") Set<SkillRequest> skills, @JsonProperty("status") CandidateStatus status,
                        @JsonProperty("preferredLocation") LocationRequest preferredLocation, @JsonProperty("preferredSalary")  Salary preferredSalary,
                        @JsonProperty("expectations") Set<String> expectations, @JsonProperty("preferredCompanyType") List<String> preferredCompanyType,
                        @JsonProperty("experiences") List<Experience> experiences, @JsonProperty("educations") List<Education> educations,
                        @JsonProperty("preferredLang") List<Language> preferredLang) {
    this.id = id;
    this.owner = owner;
    this.photoUrl = photoUrl;
    this.selectedJobRole = selectedJobRole;
    this.skills = skills;
    this.status = status;
    this.preferredLocation = preferredLocation;
    this.preferredSalary = preferredSalary;
    this.expectations = expectations;
    this.preferredCompanyType = preferredCompanyType;
    this.experiences = experiences;
    this.educations = educations;
    this.preferredLang = preferredLang;
  }
}

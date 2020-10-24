package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mangalaxy.mango.domain.CandidateStatus;
import com.mangalaxy.mango.domain.entity.Education;
import com.mangalaxy.mango.domain.entity.Experience;
import com.mangalaxy.mango.domain.entity.Language;
import com.mangalaxy.mango.domain.entity.Salary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(doNotUseGetters = true)
public class ProfileResponse {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("owner")
  private TalentResponse owner;

  @JsonProperty("photoUrl")
  private String photoUrl;

  @JsonProperty("selectedJobRole")
  private String selectedJobRole;

  @JsonProperty("status")
  private CandidateStatus status;

  @JsonProperty("preferredLocation")
  private LocationResponse preferredLocation;

  @JsonProperty("preferredSalary")
  private Salary preferredSalary;

  @JsonProperty("expectations")
  private Set<String> expectations;

  @JsonProperty("preferredCompanyType")
  private List<String> preferredCompanyType;

  @JsonProperty("experiences")
  private List<Experience> experiences;

  @JsonProperty("educations")
  private List<Education> educations;

  @JsonProperty("preferredLanguage")
  private List<Language> preferredLanguage;

  @JsonProperty("skills")
  private Set<SkillResponse> skills;

}

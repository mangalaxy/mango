package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString(doNotUseGetters = true)
public class JobResponse {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("jobRole")
  private String jobRoleTitle;

  @JsonProperty("employmentType")
  private String employmentType;

  @JsonProperty("city")
  private String locationCity;

  @JsonProperty("country")
  private String locationCountry;

  @JsonProperty("remote")
  private Boolean remote;

  @JsonProperty("relocation")
  private Boolean relocation;

  @JsonProperty("visaSponsorship")
  private Boolean visaSponsorship;

  @JsonProperty("requiredExperience")
  private String requiredExperience;

  @JsonProperty("skills")
  private Set<SkillResponse> skills;

  @JsonProperty("createdDate")
  private LocalDateTime createdDate;

  @JsonProperty("modifiedDate")
  private LocalDateTime modifiedDate;

}

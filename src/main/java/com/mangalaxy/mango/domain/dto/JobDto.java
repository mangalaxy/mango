package com.mangalaxy.mango.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class JobDto {

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

  @JsonProperty("createdDate")
  private LocalDateTime createdDate;

  @JsonProperty("modifiedDate")
  private LocalDateTime modifiedDate;

}

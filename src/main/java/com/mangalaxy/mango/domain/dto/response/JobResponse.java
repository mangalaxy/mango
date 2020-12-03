package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString(doNotUseGetters = true)
public class JobResponse {
  Long id;
  String title;
  String jobRoleTitle;
  String jobType;
  LocationResponse location;
  Boolean remote;
  Boolean relocation;
  Boolean visaSponsorship;
  String experienceRequired;
  String description;
  Set<SkillResponse> skills;
  LocalDateTime createdDate;
  LocalDateTime modifiedDate;
}

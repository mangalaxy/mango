package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString(doNotUseGetters = true)
public class JobResponse {
  private Long id;
  private String title;
  private String jobRoleTitle;
  private String jobType;
  private LocationResponse location;
  private Boolean remote;
  private Boolean relocation;
  private Boolean visaSponsorship;
  private String experienceRequired;
  private String description;
  private Set<SkillResponse> skills;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime createdDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime modifiedDate;
}

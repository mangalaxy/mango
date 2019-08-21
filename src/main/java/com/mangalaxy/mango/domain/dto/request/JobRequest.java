package com.mangalaxy.mango.domain.dto.request;

import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.dto.response.SkillResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class JobRequest {
  private Long id;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private String title;
  private String employmentType;
  private Boolean isRemote;
  private Boolean isRelocate;
  private Boolean isVisaSponsorship;
  private String xpRange;
  private LocationResponse location;
  private Set<SkillResponse> skills;
  private EmployerResponse publisher;
  private String jobRole;
}

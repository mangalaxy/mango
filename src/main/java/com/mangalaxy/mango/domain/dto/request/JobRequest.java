package com.mangalaxy.mango.domain.dto.request;

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
  private final LocationResponse location;
  private final Set<SkillResponse> skills;
  private final EmployerResponse publisher;
  private final String jobRole;
}

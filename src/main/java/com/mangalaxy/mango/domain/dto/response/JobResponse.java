package com.mangalaxy.mango.domain.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class JobResponse {
  private Long id;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private String title;
  private String employmentType;
  private Boolean isRemote;
  private Boolean isRelocate;
  private Boolean isVisaSponsorship;
  private String xpRange;
  private String jobRole;
}

package com.mangalaxy.mango.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a published job by the employer.
 */
@Data
public class Job {
  private Long id;
  private String position;
  // TODO: Change to EmploymentType enumeration
  private String employmentType;
  private Boolean isRemote;
  private Boolean isRelocate;
  private Boolean isVisaSponsorship;
  private String experience;
  private Location location;
  private Set<Skill> techStack = new HashSet<>();
  private Employer publisher;
  private String jobRole;
  private LocalDateTime publishedAt;
  private LocalDateTime updatedAt;
}

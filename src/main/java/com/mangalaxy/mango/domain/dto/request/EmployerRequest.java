package com.mangalaxy.mango.domain.dto.request;

import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.Location;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EmployerRequest {
  private Long id;
  private String password;
  private String fullName;
  private String workEmail;
  private String phoneNumber;
  private Company company;
  private String jobTitle;
  private String photoUrl;
  private Location location;
  private Set<Job> openJobs;
}

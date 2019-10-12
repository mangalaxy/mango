package com.mangalaxy.mango.domain.dto.response;

import com.mangalaxy.mango.domain.entity.Company;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EmployerResponse {
  private Long id;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private String fullName;
  private String workEmail;
  private String phoneNumber;
  private Company company;
  private String jobTitle;
  private String photoUrl;
  private Set<TalentResponse> talents;
}

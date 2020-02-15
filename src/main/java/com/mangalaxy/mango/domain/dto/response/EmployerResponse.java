package com.mangalaxy.mango.domain.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class EmployerResponse {
  private Long id;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private String fullName;
  private String email;
  private String phoneNumber;
  private CompanyResponse company;
  private String jobTitle;
  private String photoUrl;
  private Set<TalentResponse> talents;
}

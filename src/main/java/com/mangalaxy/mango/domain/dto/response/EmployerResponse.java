package com.mangalaxy.mango.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployerResponse {
  private Long id;
  private String fullName;
  private String email;
  private String phoneNumber;
  private String companyName;
  private String jobTitle;
  private String photoUrl;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
}

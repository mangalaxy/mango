package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployerResponse {
  private final Long id;
  private final String fullName;
  private final String email;
  private final String phoneNumber;
  private final String companyName;
  private final String jobTitle;
  private final String photoUrl;
  private final LocationResponse location;
  private final LocalDateTime createdDate;
  private final LocalDateTime lastModifiedDate;
}

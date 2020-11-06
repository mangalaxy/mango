package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"fullName", "email"})
@ToString(doNotUseGetters = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployerResponse {
  Long id;
  String fullName;
  String email;
  String phoneNumber;
  String companyName;
  String jobTitle;
  String photoUrl;
  LocationResponse location;
  LocalDateTime createdDate;
  LocalDateTime modifiedDate;
}

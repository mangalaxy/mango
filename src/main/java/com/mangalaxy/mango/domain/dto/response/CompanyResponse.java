package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CompanyResponse {
  Long id;
  String name;
  String headline;
  String logoUrl;
  String headquartersAddress;
  String size;
  String industry;
  String promoUrl;
  String about;
  Set<SkillResponse> skills;
  Set<String> perks;
  Set<String> benefits;
  Set<String> links;
  Set<String> photos;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  LocalDateTime createdDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  LocalDateTime modifiedDate;
}

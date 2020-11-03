package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CompanyResponse {
  Long id;
  String name;
  String headline;
  String logoUrl;
  String headquarterAddress;
  String size;
  String industry;
  String promoUrl;
  String about;
  Set<SkillResponse> skills;
  Set<String> perks;
  Set<String> benefits;
  Set<String> links;
  Set<String> photos;
  LocalDateTime createdDate;
  LocalDateTime modifiedDate;
}

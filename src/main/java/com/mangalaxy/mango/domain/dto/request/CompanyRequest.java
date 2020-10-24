package com.mangalaxy.mango.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class CompanyRequest {
  private String name;
  private String headline;
  private String logo;
  private String headquarterAddress;
  private String size;
  private String industry;
  private String promo;
  private String about;
}

package com.mangalaxy.mango.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// TODO: Add company perks, benefits, links attributes
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class CompanyResponse {
  private Long id;
  private String name;
  private String headline;
  private String logo;
  private String headquarterAddress;
  private String size;
  private String industry;
  private String promo;
  private String about;
}

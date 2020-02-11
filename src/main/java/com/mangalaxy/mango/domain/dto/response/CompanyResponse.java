package com.mangalaxy.mango.domain.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyResponse {
  private Long id;
  private String name;
  private String headline;
  private String logo;
  private String headquartersAddress;
  private String size;
  private String industry;
  private String promo;
  private String about;
}

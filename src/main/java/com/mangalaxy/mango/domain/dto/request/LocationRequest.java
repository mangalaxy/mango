package com.mangalaxy.mango.domain.dto.request;

import lombok.Data;

@Data
public class LocationRequest {
  private Long id;
  private String city;
  private String country;
}

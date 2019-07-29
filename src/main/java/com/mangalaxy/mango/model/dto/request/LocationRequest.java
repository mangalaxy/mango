package com.mangalaxy.mango.model.dto.request;

import lombok.Data;

@Data
public class LocationRequest {
  private Long id;
  private String city;
  private String country;
}

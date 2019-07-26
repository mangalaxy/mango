package com.mangalaxy.mango.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class LocationRequest {
  private Long id;
  private String city;
  private String country;
}

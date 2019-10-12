package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequest {
  private final Long id;
  private final String city;
  private final String country;

  @JsonCreator
  public LocationRequest(@JsonProperty("id") Long id,
                         @JsonProperty("city") String city,
                         @JsonProperty("country") String country) {
    this.id = id;
    this.city = city;
    this.country = country;
  }
}

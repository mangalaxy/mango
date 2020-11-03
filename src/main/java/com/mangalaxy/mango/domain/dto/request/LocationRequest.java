package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class LocationRequest {
  Short id;
  String city;
  String country;

  @JsonCreator
  public LocationRequest(
        @JsonProperty("id") Short id,
        @JsonProperty("city") String city,
        @JsonProperty("country") String country) {
    this.id = id;
    this.city = city;
    this.country = country;
  }
}

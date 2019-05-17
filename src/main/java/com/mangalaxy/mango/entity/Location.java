package com.mangalaxy.mango.entity;

import lombok.Data;

/**
 * Represents a geographical location.
 * <p>
 * It's includes only a specific country and city, it does not include
 * such geographical attributes as latitude and longitude.
 */
@Data
public class Location {

  private Long id;
  private String city;
  private String country;
}

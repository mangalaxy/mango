package com.mangalaxy.mango.util;

import com.google.common.primitives.Shorts;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class Samples {

  public Location createLocation() {
    Location location = new Location();
    location.setId(Shorts.checkedCast(1L));
    location.setCity("Berlin");
    location.setCountry("Germany");
    return location;
  }

  public Employer createEmployer() {
    Employer employer = new Employer();
    employer.setId(1L);
    employer.setFullName("Maria Fisher");
    employer.setEmail("m.fisher@gmail.com");
    employer.setPassword("AS3$hj67mf1983");
    employer.setLocation(createLocation());
    employer.setCreatedDate(LocalDateTime.now());
    return employer;
  }
}

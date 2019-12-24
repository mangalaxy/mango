package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
  Page<LocationResponse> getAllLocations(Pageable pageable);

  LocationResponse getLocationById(Short id);
}

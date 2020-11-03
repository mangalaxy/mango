package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;

import java.util.List;

public interface LocationService {
  List<LocationResponse> getAllLocations();
  LocationResponse getLocationById(Short id);
}

package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class LocationController {
  private final LocationService locationService;

  @GetMapping("/api/v1/locations")
  public ResponseEntity<List<LocationResponse>> getAllLocations() {
    List<LocationResponse> locationList = locationService.getAllLocations();
    return ResponseEntity.ok(locationList);
  }

  @GetMapping("/api/v1/locations/{locationId}")
  public ResponseEntity<LocationResponse> getLocationById(@PathVariable Short locationId) {
    LocationResponse location = locationService.getLocationById(locationId);
    return ResponseEntity.ok(location);
  }
}

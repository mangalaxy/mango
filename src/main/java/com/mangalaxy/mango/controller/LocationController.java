package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.service.LocationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Locations API", description = "Provides read operations for location resource")
@RequiredArgsConstructor
@RestController
public class LocationController {
  private final LocationService locationService;

  @GetMapping("/api/v1/locations")
  public ResponseEntity<List<LocationResponse>> getAllLocations() {
    List<LocationResponse> locationResponses = locationService.getAllLocations();
    return ResponseEntity.ok(locationResponses);
  }

  @GetMapping("/api/v1/locations/{locationId}")
  public ResponseEntity<LocationResponse> getSpecifiedLocation(@PathVariable("locationId") Short id) {
    LocationResponse location = locationService.getLocationById(id);
    return ResponseEntity.ok(location);
  }
}

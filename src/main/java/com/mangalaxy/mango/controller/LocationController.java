package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {
  private final LocationService locationService;

  @GetMapping
  public ResponseEntity<List<LocationResponse>> getAllLoctions() {
    List<LocationResponse> locations = locationService.getAllLocations();
    return ResponseEntity.ok(locations);
  }

  @GetMapping("{id}")
  public ResponseEntity<LocationResponse> getLocationById(@PathVariable Short id) {
    LocationResponse location = locationService.getLocationById(id);
    return ResponseEntity.ok(location);
  }
}

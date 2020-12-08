package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.repository.LocationRepository;
import com.mangalaxy.mango.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {
  private final LocationRepository locationRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<LocationResponse> getAllLocations() {
    Iterable<Location> locations = locationRepository.findAll();
    return StreamSupport.stream(locations.spliterator(), false)
          .map(location -> modelMapper.map(location, LocationResponse.class))
          .collect(toList());
  }

  @Override
  public LocationResponse getLocationById(Short id) {
    Location location = locationRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("location", "id", id));
    return modelMapper.map(location, LocationResponse.class);
  }
}

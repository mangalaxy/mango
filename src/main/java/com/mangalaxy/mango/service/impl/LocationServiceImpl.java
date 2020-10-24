package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.LocationRepository;
import com.mangalaxy.mango.service.LocationService;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
  private final LocationRepository locationRepository;
  private final ModelMapper modelMapper;

  @Override
  public List<LocationResponse> getAllLocations() {
    List<Location> locations = (List<Location>) locationRepository.findAll();
    return locations.stream().map(location -> modelMapper.map(location, LocationResponse.class))
          .collect(toList());
  }

  @Override
  public LocationResponse getLocationById(Short id) {
    Location location = locationRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return modelMapper.map(location, LocationResponse.class);
  }
}

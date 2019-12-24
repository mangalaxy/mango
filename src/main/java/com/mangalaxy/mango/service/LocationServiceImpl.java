package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.LocationRepository;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
  private final LocationRepository locationRepository;
  private final ModelMapper modelMapper;

  @Override
  public Page<LocationResponse> getAllLocations(Pageable pageable) {
    Page<Location> locations = locationRepository.findAll(pageable);
    return locations.map(location -> modelMapper.map(location, LocationResponse.class));
  }

  @Override
  public LocationResponse getLocationById(Short id) {
    Location location = locationRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return modelMapper.map(location, LocationResponse.class);
  }
}

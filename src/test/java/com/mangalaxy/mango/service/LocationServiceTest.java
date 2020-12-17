package com.mangalaxy.mango.service;

import com.google.common.primitives.Shorts;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.LocationRepository;
import com.mangalaxy.mango.service.impl.LocationServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
  @Mock
  private LocationRepository locationRepository;
  private LocationService locationService;

  private Location location1;
  private Location location2;

  @BeforeEach
  void setUp() {
    ModelMapper modelMapper = new ModelMapper();
    locationService = new LocationServiceImpl(locationRepository, modelMapper);

    location1 = new Location();
    location1.setId(Shorts.checkedCast(1L));
    location1.setCity("Frankfurt");
    location1.setCountry("Germany");

    location2 = new Location();
    location2.setId(Shorts.checkedCast(2L));
    location2.setCity("Kyiv");
    location2.setCountry("Ukraine");
  }

  @Test
  @DisplayName("Find a location with id 1")
  void shouldFindLocationById_thenSuccess() {
    // given
    Short expectedId = 1;
    when(locationRepository.findById(expectedId)).thenReturn(Optional.of(location1));
    // when
    LocationResponse response = locationService.getLocationById(expectedId);
    // then
    verify(locationRepository).findById(expectedId);
    assertEquals(expectedId, response.getId());
    assertEquals("Germany", response.getCountry());
  }

  @Test
  @DisplayName("Find a list with 2 locations")
  void shouldFindAllLocations_thenSuccess() {
    // given
    when(locationRepository.findAll()).thenReturn(Lists.newArrayList(location1, location2));
    // when
    List<LocationResponse> locationList = locationService.getAllLocations();
    // then
    verify(locationRepository).findAll();
    assertEquals(2, locationList.size());
  }
}

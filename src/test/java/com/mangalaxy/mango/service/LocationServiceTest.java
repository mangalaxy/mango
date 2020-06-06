package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.LocationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceTest {
  @Autowired
  private LocationService locationService;

  @MockBean
  private LocationRepository locationRepository;

  private static final Location firstMockLocation = new Location();
  private static final Location secondMockLocation = new Location();

  @Before
  public void setUp() {
    firstMockLocation.setId((short) 1);
    firstMockLocation.setCity("Frankfurt");
    firstMockLocation.setCountry("Germany");

    secondMockLocation.setId((short) 2);
    secondMockLocation.setCity("Kyiv");
    secondMockLocation.setCountry("Ukraine");
  }

  @Test
  public void getLocationByIdTest() {
    Short expectedId = 1;
    String expectedCountry = "Germany";

    Mockito.when(locationRepository.findById(expectedId)).thenReturn(java.util.Optional.of(firstMockLocation));
    LocationResponse response = locationService.getLocationById(expectedId);

    Mockito.verify(locationRepository).findById(expectedId);
    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals(expectedCountry, response.getCountry());
  }

  @Test
  public void getAllLocations() {
    int expectedSize = 2;
    List<Location> locations = new ArrayList<>();
    locations.add(firstMockLocation);
    locations.add(secondMockLocation);

    Mockito.when(locationRepository.findAll()).thenReturn(locations);
    List<LocationResponse> responses = locationService.getAllLocations();

    Mockito.verify(locationRepository).findAll();
    Assert.assertEquals(expectedSize, responses.size());

  }
}

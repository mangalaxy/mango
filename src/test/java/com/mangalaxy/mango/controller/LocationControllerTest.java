package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.service.LocationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser(username = "test@gmail.com")
public class LocationControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void shouldFindLocationById() throws Exception {
    Short expectedId = 1;
    String expectedCountry = "Ukraine";

    MvcResult result = mockMvc.perform(get("/api/v1/locations/1")).andReturn();
    String response = result.getResponse().getContentAsString();
    LocationResponse locationResponse = objectMapper.readValue(response, LocationResponse.class);

    Assert.assertEquals(expectedId, locationResponse.getId());
    Assert.assertEquals(expectedCountry, locationResponse.getCountry());
  }

  @Test
  public void shouldGetAllLocations() throws Exception {
    int expectedSize = 2;

    MvcResult result = mockMvc.perform(get("/api/v1/locations")).andReturn();
    String response = result.getResponse().getContentAsString();
    List<LocationResponse> locations = objectMapper.readValue(response, new TypeReference<List<LocationResponse>>(){});

    Assert.assertEquals(expectedSize, locations.size());
  }
}

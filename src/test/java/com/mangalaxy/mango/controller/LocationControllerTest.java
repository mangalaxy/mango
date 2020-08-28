package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.service.LocationService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = LocationController.class,
      useDefaultFilters = false,
      includeFilters = {
            @ComponentScan.Filter(
                  type = FilterType.ASSIGNABLE_TYPE,
                  value = LocationController.class)
      })
@AutoConfigureMockMvc(addFilters = false)
class LocationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private LocationService locationService;

  private LocationResponse locationResponse1;
  private LocationResponse locationResponse2;
  private List<LocationResponse> locationList;

  @BeforeEach
  void setUp() {
    locationResponse1 = new LocationResponse((short) 1, "Austin", "USA");
    locationResponse2 = new LocationResponse((short) 2, "Boston", "USA");
    locationList = Lists.newArrayList(locationResponse1, locationResponse2);
  }

  @Test
  @DisplayName("Find the location resource with ID: 1")
  void shouldReturnLocationResponseAndStatusOk() throws Exception {
    Short id = 1;
    String expectedBody = objectMapper.writeValueAsString(locationResponse1);
    given(locationService.getLocationById(anyShort())).willReturn(locationResponse1);
    mockMvc.perform(get("/api/v1/locations/{id}", id)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedBody));
    verify(locationService).getLocationById(anyShort());
  }

  @Test
  @DisplayName("Returns location list with size 2")
  void shouldReturnAllLocationsAndStatusOk() throws Exception {
    String expectedBody = objectMapper.writeValueAsString(locationList);
    given(locationService.getAllLocations()).willReturn(locationList);
    mockMvc.perform(get("/api/v1/locations")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedBody));

    verify(locationService).getAllLocations();
  }
}

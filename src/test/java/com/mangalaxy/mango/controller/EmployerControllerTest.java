package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.service.EmployerRelationshipService;
import com.mangalaxy.mango.service.EmployerService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = EmployerController.class, useDefaultFilters = false,
      includeFilters = {
            @ComponentScan.Filter(
                  type = FilterType.ASSIGNABLE_TYPE,
                  value = EmployerController.class)
      })
@AutoConfigureMockMvc(addFilters = false)
class EmployerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private EmployerService employerService;
  @MockBean
  private EmployerRelationshipService employerRelationshipService;

  private EmployerResponse employerResponse1;
  private EmployerResponse employerResponse2;
  private List<EmployerResponse> employerResponseList;

  @BeforeEach
  void setUp() {
    employerResponse1 = EmployerResponse.builder()
          .id(1L)
          .fullName("Cortney Swiss")
          .email("cort.sw@yahoo.com")
          .jobTitle("IT Acquisition recruiter")
          .companyName("Zalando")
          .createdDate(LocalDateTime.now())
          .build();
    employerResponse2 = EmployerResponse.builder()
          .id(2L)
          .fullName("Mark Darton")
          .email("mark12@gmail.com")
          .jobTitle("Executive HR")
          .companyName("H & F")
          .createdDate(LocalDateTime.now())
          .build();
    employerResponseList = Lists.newArrayList(employerResponse1, employerResponse2);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  @Test
  @DisplayName("Find the employer with ID: 1")
  void shouldReturnSerializedEmployerResponseByIdAndStatusOk() throws Exception {
    given(employerService.fetchEmployerById(anyLong())).willReturn(employerResponse1);
    String expectedJson = objectMapper.writeValueAsString(employerResponse1);
    mockMvc.perform(get("/api/v1/employers/1")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    verify(employerService).fetchEmployerById(anyLong());
  }

  @Test
  @DisplayName("Returns the first page with two employers")
  void shouldReturnsTwoEmployersAndStatusOk() throws Exception {
    Page<EmployerResponse> employerPage = new PageImpl<>(employerResponseList);
    String expectedJson = objectMapper.writeValueAsString(employerPage);
    given(employerService.fetchAllEmployers(any(Pageable.class))).willReturn(employerPage);
    mockMvc.perform(get("/api/v1/employers?page=0&limit=20")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));
    verify(employerService).fetchAllEmployers(any(Pageable.class));
    verify(employerRelationshipService, never())
          .fetchBookmarkedTalents(anyLong(), any(Pageable.class));
  }

  @Test
  @DisplayName("Create a new employer and check status 201")
  void shouldCreateNewEmployerAndReturnsStatusCreated() throws Exception {
    EmployerRequest newEmployer = EmployerRequest.builder()
          .fullName("Erik Wish")
          .email("erik1000_wish@gmail.com")
          .password("12$qty789")
          .jobTitle("IT Recruitment Generalist")
          .companyName("Okta c.l.")
          .location(new LocationRequest((short) 67, "San Diego", "USA"))
          .build();
    EmployerResponse mockEmployer = EmployerResponse.builder()
          .id(1L)
          .fullName("Erik Wish")
          .email("erik1000_wish@gmail.com")
          .jobTitle("IT Recruitment Generalist")
          .companyName("Okta c.l.")
          .location(new LocationResponse((short) 67, "San Diego", "USA"))
          .createdDate(LocalDateTime.now())
          .build();

    given(employerService.createNewEmployer(any(EmployerRequest.class))).willReturn(mockEmployer);
    mockMvc.perform(post("/api/v1/employers")
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName())
          .content(objectMapper.writeValueAsString(newEmployer)))
          .andDo(print())
          .andExpect(status().isCreated())
          .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/employers/1"))
          .andReturn();

    ArgumentCaptor<EmployerRequest> argumentCaptor = ArgumentCaptor.forClass(EmployerRequest.class);
    verify(employerService).createNewEmployer(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue().getFullName()).isEqualTo("Erik Wish");
    assertThat(argumentCaptor.getValue().getEmail()).isEqualTo("erik1000_wish@gmail.com");
  }

  @Test
  @DisplayName("Update an existing employer with updated job title")
  public void shouldUpdateEmployerWithJobTitleAndReturnsStatusOk() throws Exception {
    String updatedJobTitle = "HR Generalist";
    EmployerRequest updatedEmployer = EmployerRequest.builder()
          .fullName("Erik Wish")
          .email("erik1000_wish@gmail.com")
          .password("12$qty789")
          .jobTitle(updatedJobTitle)
          .companyName("Okta c.l.")
          .location(new LocationRequest((short) 67, "San Diego", "USA"))
          .build();
    EmployerResponse mockEmployer = EmployerResponse.builder()
          .id(1L)
          .fullName("Erik Wish")
          .email("erik1000_wish@gmail.com")
          .jobTitle(updatedJobTitle)
          .companyName("Okta c.l.")
          .location(new LocationResponse((short) 67, "San Diego", "USA"))
          .createdDate(LocalDateTime.now().minusDays(17).truncatedTo(ChronoUnit.SECONDS))
          .modifiedDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
          .build();
    String expectedJson = objectMapper.writeValueAsString(mockEmployer);
    given(employerService.updateEmployerById(anyLong(), any(EmployerRequest.class))).willReturn(mockEmployer);

    mockMvc.perform(put("/api/v1/employers/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(updatedEmployer))
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    ArgumentCaptor<EmployerRequest> argumentCaptor = ArgumentCaptor.forClass(EmployerRequest.class);
    verify(employerService).updateEmployerById(anyLong(), argumentCaptor.capture());
    assertThat(argumentCaptor.getValue().getFullName()).isEqualTo("Erik Wish");
    assertThat(argumentCaptor.getValue().getJobTitle()).isEqualTo(updatedJobTitle);
  }

  @Test
  @DisplayName("Return status 404 when employer not found")
  void shouldReturnsStatus404WhenEmployerDoesntExist() throws Exception {
    Long employerId = 1L;
    willThrow(new ResourceNotFoundException()).given(employerService).fetchEmployerById(employerId);
    MvcResult mvcResult = mockMvc.perform(get("/api/v1/employers/{employerId}", employerId)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound())
          .andReturn();
    verify(employerService).fetchEmployerById(employerId);
    assertThat(mvcResult.getResolvedException()).isInstanceOf(ResourceNotFoundException.class);
    assertThat(mvcResult.getResolvedException()).hasMessage("The resource with the specified ID does not exist");
  }

  @Test
  @DisplayName("Delete specified employer and return status 204")
  void shouldDeleteEmployerByIdAndReturnsStatus204() throws Exception {
    Long employerId = 1L;
    doNothing().when(employerService).deleteEmployerById(employerId);
    mockMvc.perform(delete("/api/v1/employers/{employerId}", employerId)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNoContent());
    verify(employerService).deleteEmployerById(employerId);
  }
}

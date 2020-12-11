package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.request.TalentSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.TalentUpdateRequest;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.service.TalentService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = TalentController.class, useDefaultFilters = false,
      includeFilters = {
      @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            value = TalentController.class)
})
@AutoConfigureMockMvc(addFilters = false)
class TalentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private TalentService talentService;

  private TalentResponse talentResponse1;
  private TalentResponse talentResponse2;
  private TalentResponse talentResponse3;
  private List<TalentResponse> talentResponseList;

  @BeforeEach
  void setUp() {
    talentResponse1 = new TalentResponse(1L, "John Doe",
          "john.doe12@gmail.com", null, LocalDateTime.now(), null);
    talentResponse2 = new TalentResponse(2L, "Thomas Miller",
          "t.miller@band.de", null, LocalDateTime.now(), null);
    talentResponse3 = new TalentResponse(3L, "Joanna Denver",
          "jd_denver1983@mail.uk", null, LocalDateTime.now(), null);
    talentResponseList = Lists.newArrayList(talentResponse1, talentResponse2, talentResponse3);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  @Test
  @DisplayName("Find the talent with ID: 1")
  void shouldReturnTalentResponseByIdAndStatusOk() throws Exception {
    // stubbing mock method
    given(talentService.fetchTalentById(anyLong())).willReturn(talentResponse1);
    String expectedJson = objectMapper.writeValueAsString(talentResponse1);
    mockMvc.perform(get("/api/v1/talents/1")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    verify(talentService).fetchTalentById(anyLong());
  }

  @Test
  @DisplayName("Return the first page with two talents")
  void shouldReturnTwoTalentsAndStatusOk() throws Exception {
    Page<TalentResponse> talentPage = new PageImpl<>(talentResponseList);
    String expectedJson = objectMapper.writeValueAsString(talentPage);
    given(talentService.fetchTalentPage(any(Pageable.class))).willReturn(talentPage);
    mockMvc.perform(get("/api/v1/talents?page=0&limit=20")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    verify(talentService).fetchTalentPage(any(Pageable.class));
  }

  @Test
  @Disabled("Talent creation has been moved to Register Controller")
  @DisplayName("Create a new talent and check status 201")
  void shouldCreateNewTalentAndReturnStatus201() throws Exception {
    TalentSignUpRequest newTalent = new TalentSignUpRequest(
          "Anna Fisher",
          "anna_fisher@gmail.com",
          "12g27gd2",
          new LocationRequest((short) 1, "Berlin", "Germany")
    );
    TalentResponse mockTalent = new TalentResponse(
          1L,
          "Anna Fisher",
          "anna_fisher@gmail.com",
          new LocationResponse((short) 1, "Berlin", "Germany"),
          LocalDateTime.now(), null);
    given(talentService.createNewTalent(any(TalentSignUpRequest.class))).willReturn(mockTalent);
    mockMvc.perform(post("/api/v1/talents")
          .content(objectMapper.writeValueAsString(newTalent))
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isCreated())
          .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/talents/1"));

    ArgumentCaptor<TalentSignUpRequest> talentCaptor = ArgumentCaptor.forClass(TalentSignUpRequest.class);
    verify(talentService).createNewTalent(talentCaptor.capture());
    assertThat(talentCaptor.getValue().getFullName()).isEqualTo("Anna Fisher");
    assertThat(talentCaptor.getValue().getEmail()).isEqualTo("anna_fisher@gmail.com");
  }

  @Test
  @DisplayName("Update an existing talent with updated email")
  void shouldUpdateTalentWithEmailAndReturnsStatusOk() throws Exception {
    // prepare data
    String updatedEmail = "a.fisher1290@gmail.com";
    TalentSignUpRequest updatedTalent = new TalentSignUpRequest(
          "Anna Fisher",
          updatedEmail,
          "12g27gd2",
          new LocationRequest((short) 1, "Berlin", "Germany")
    );
    TalentResponse mockTalent = new TalentResponse(
          1L,
          "Anna Fisher",
          updatedEmail,
          new LocationResponse((short) 1, "Berlin", "Germany"),
          LocalDateTime.now(), null);
    String expectedJson = objectMapper.writeValueAsString(mockTalent);
    given(talentService.updateTalentById(anyLong(), any(TalentUpdateRequest.class))).willReturn(mockTalent);
    mockMvc.perform(put("/api/v1/talents/1")
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(updatedTalent))
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedJson));

    ArgumentCaptor<TalentUpdateRequest> talentCaptor = ArgumentCaptor.forClass(TalentUpdateRequest.class);
    verify(talentService).updateTalentById(anyLong(), talentCaptor.capture());
    assertThat(talentCaptor.getValue().getFullName()).isEqualTo("Anna Fisher");
    assertThat(talentCaptor.getValue().getEmail()).isEqualTo(updatedEmail);
  }

  @Test
  @DisplayName("Check status 404 when talent not found")
  void shouldReturnsStatus404WhenTalentDoesntExist() throws Exception {
    Long talentId = 1L;
    willThrow(new ResourceNotFoundException("talent", "id", talentId)).given(talentService).fetchTalentById(talentId);
    MvcResult mvcResult = mockMvc.perform(get("/api/v1/talents/{talentId}", talentId)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound())
          .andReturn();
    verify(talentService).fetchTalentById(talentId);
    assertThat(mvcResult.getResolvedException()).isInstanceOf(ResourceNotFoundException.class);
    assertThat(mvcResult.getResolvedException()).hasMessage("talent not found with id : '1'");
  }

  @Test
  @DisplayName("Delete a talent by ID and check status 204")
  void shouldDeleteTalentByIdAndReturnsStatus204() throws Exception {
    Long talentId = 1L;
    doNothing().when(talentService).deleteTalentById(talentId);
    mockMvc.perform(delete("/api/v1/talents/{talentId}", talentId)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNoContent());
    verify(talentService).deleteTalentById(talentId);
  }
}
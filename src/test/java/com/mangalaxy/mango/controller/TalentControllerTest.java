package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.service.TalentService;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.skyscreamer.jsonassert.JSONAssert;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
          "john.doe12@gmail.com", null);
    talentResponse2 = new TalentResponse(2L, "Thomas Miller",
          "t.miller@band.de", null);
    talentResponse3 = new TalentResponse(3L, "Joanna Denver",
          "jd_denver1983@mail.uk", null);
    talentResponseList = Lists.newArrayList(talentResponse1, talentResponse2, talentResponse3);
  }

  @Test
  @DisplayName("Find the talent with ID: 1")
  void whenEnterTalentId_thenReturnsSerializedTalentResponseAndStatusOk()
        throws Exception {
    // stubbing mock method
    given(talentService.getTalentById(anyLong())).willReturn(talentResponse1);
    String expectedBody = objectMapper.writeValueAsString(talentResponse1);

    MvcResult mvcResult = mockMvc.perform(get("/api/v1/talents/1")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andReturn();

    verify(talentService).getTalentById(anyLong());
    JSONAssert.assertEquals(expectedBody, mvcResult.getResponse().getContentAsString(), false);
  }

  @Test
  @DisplayName("Returns the first page with two talents")
  void shouldReturnsTwoTalentsAndStatusOk() throws Exception {
    Page<TalentResponse> talentPage = new PageImpl<>(talentResponseList);
    String expectedBody = objectMapper.writeValueAsString(talentPage);
    given(talentService.findAll(any(Pageable.class))).willReturn(talentPage);

    MvcResult mvcResult = mockMvc.perform(get("/api/v1/talents?page=0&limit=20")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andReturn();

    verify(talentService).findAll(any(Pageable.class));
    JSONAssert.assertEquals(expectedBody, mvcResult.getResponse().getContentAsString(), false);
  }

  @Test
  @DisplayName("Create a new talent and check status 201")
  void shouldCreateNewTalentAndReturnsStatusCreated() throws Exception {
    TalentRequest newTalent = new TalentRequest(
          "Anna Fisher",
          "anna_fisher@gmail.com",
          "12g27gd2",
          new LocationRequest((short) 1, "Berlin", "Germany")
    );
    String newTalentJson = objectMapper.writeValueAsString(newTalent);
    TalentResponse mockTalent = new TalentResponse(
          1L,
          "Anna Fisher",
          "anna_fisher@gmail.com",
          new LocationResponse((short) 1, "Berlin", "Germany"));
    given(talentService.createNewTalent(any(TalentRequest.class))).willReturn(mockTalent);

    mockMvc.perform(post("/api/v1/talents")
          .content(newTalentJson)
          .contentType(MediaType.APPLICATION_JSON_UTF8))
          .andDo(print())
          .andExpect(status().isCreated())
          .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/talents/1"))
          .andReturn();

    ArgumentCaptor<TalentRequest> talentCaptor = ArgumentCaptor.forClass(TalentRequest.class);
    verify(talentService).createNewTalent(talentCaptor.capture());
    assertThat(talentCaptor.getValue().getFullName()).isEqualTo("Anna Fisher");
    assertThat(talentCaptor.getValue().getEmail()).isEqualTo("anna_fisher@gmail.com");
  }

  @Test
  @DisplayName("Update an existing talent with updated email")
  public void shouldUpdateTalentWithEmailAndReturnsStatusOk() throws Exception {
    // prepare data
    String updatedEmail = "a.fisher1290@gmail.com";
    TalentRequest updatedTalent = new TalentRequest(
          "Anna Fisher",
          updatedEmail,
          "12g27gd2",
          new LocationRequest((short) 1, "Berlin", "Germany")
    );
    String talentJson = objectMapper.writeValueAsString(updatedTalent);
    TalentResponse mockTalent = new TalentResponse(
          1L,
          "Anna Fisher",
          updatedEmail,
          new LocationResponse((short) 1, "Berlin", "Germany"));
    String expectedBody = objectMapper.writeValueAsString(mockTalent);
    given(talentService.updateTalent(any(TalentRequest.class), anyLong())).willReturn(mockTalent);

    MvcResult mvcResult = mockMvc.perform(put("/api/v1/talents/1")
          .contentType(MediaType.APPLICATION_JSON_UTF8)
          .content(talentJson))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andReturn();

    ArgumentCaptor<TalentRequest> talentCaptor = ArgumentCaptor.forClass(TalentRequest.class);
    verify(talentService).updateTalent(talentCaptor.capture(), anyLong());
    assertThat(talentCaptor.getValue().getFullName()).isEqualTo("Anna Fisher");
    assertThat(talentCaptor.getValue().getEmail()).isEqualTo(updatedEmail);
    JSONAssert.assertEquals(expectedBody, mvcResult.getResponse().getContentAsString(), false);
  }

  @Test
  @DisplayName("Check status 404 when talent not found")
  void shouldReturnsStatus404WhenTalentDoesntExist() throws Exception {
    Long talentId = 1L;
    willThrow(new ResourceNotFoundException()).given(talentService).getTalentById(talentId);

    MvcResult mvcResult = mockMvc.perform(get("/api/v1/talents/{talentId}", talentId)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNotFound())
          .andReturn();
    Exception resolvedExc = mvcResult.getResolvedException();
    verify(talentService).getTalentById(talentId);
    assertTrue(resolvedExc instanceof ResourceNotFoundException);
    assertEquals("Resource with specified ID not found", resolvedExc.getMessage());
  }

  @Test
  @DisplayName("Delete a talent by ID and check status 204")
  void shouldDeleteTalentByIdAndReturnsStatus204() throws Exception {
    Long talentId = 1L;
    doNothing().when(talentService).deleteTalent(talentId);
    mockMvc.perform(delete("/api/v1/talents/{talentId}", talentId)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNoContent());
    verify(talentService).deleteTalent(talentId);
  }
}
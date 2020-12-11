package com.mangalaxy.mango.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mangalaxy.mango.domain.CandidateStatus;
import com.mangalaxy.mango.domain.Degree;
import com.mangalaxy.mango.domain.dto.response.LocationResponse;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.domain.dto.response.SkillResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.ActivityPeriod;
import com.mangalaxy.mango.domain.entity.Education;
import com.mangalaxy.mango.domain.entity.Experience;
import com.mangalaxy.mango.domain.entity.Language;
import com.mangalaxy.mango.domain.entity.Salary;
import com.mangalaxy.mango.service.ProfileService;
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

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProfileController.class, useDefaultFilters = false,
      includeFilters = {
            @ComponentScan.Filter(
                  type = FilterType.ASSIGNABLE_TYPE,
                  value = ProfileController.class)
      })
@AutoConfigureMockMvc(addFilters = false)
class ProfileControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProfileService profileService;

  @BeforeEach
  void setUp() {
    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
  }

  @Test
  @DisplayName("Find a talent profile by talent ID")
  void shouldReturnTalentProfileByTalentIdAndStatusOk() throws Exception {
    TalentResponse mockTalent = new TalentResponse(
          1L,
          "Anna Fisher",
          "anna_fisher@gmail.com",
          new LocationResponse((short) 1, "Berlin", "Germany"),
          LocalDateTime.now(), null);
    Education edu1 = new Education("TU Berlin", Degree.BACHELOR,
          "Tech analysis",
          new ActivityPeriod(
                LocalDate.of(1997, 9, 1),
                LocalDate.of(2002, 8, 23)
          )
    );
    Experience exp1 = new Experience("Zalando", "Acquisition Talent officer", null,
          new ActivityPeriod(LocalDate.of(2017, 3, 16), null),
          true);
    List<String> skillList = Lists.newArrayList("ES7", "VueJS", "Git", "GraphQL", "Apollo");
    Set<SkillResponse> skillSet = IntStream.range(1, skillList.size())
          .mapToObj(index -> new SkillResponse((long) index, skillList.get(index)))
          .collect(Collectors.toSet());
    ProfileResponse mockProfile = ProfileResponse.builder()
          .id(1L)
          .owner(mockTalent)
          .selectedJobRole("Software Engineering")
          .status(CandidateStatus.OFFERING)
          .preferredLocation(new LocationResponse((short) 5, "San Fransisco", "USA"))
          .preferredSalary(new Salary("USD", BigDecimal.valueOf(150000.00)))
          .preferredCompanyType(Lists.newArrayList("Startup", "Product company"))
          .preferredLanguage(Lists.newArrayList(new Language(Language.Level.ADVANCED, "English")))
          .expectations(Sets.newHashSet("Long-term contract"))
          .educations(Lists.newArrayList(edu1))
          .skills(skillSet)
          .experiences(Lists.newArrayList(exp1))
          .build();
    String expectedProfileJson = objectMapper.writeValueAsString(mockProfile);
    given(profileService.fetchTalentProfile(anyLong())).willReturn(mockProfile);

    mockMvc.perform(get("/api/v1/talents/1/profile")
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .characterEncoding(StandardCharsets.UTF_8.displayName()))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(content().json(expectedProfileJson));

    verify(profileService).fetchTalentProfile(anyLong());
  }
}

package com.mangalaxy.mango.service;

import com.google.common.primitives.Shorts;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.request.TalentSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.TalentUpdateRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.service.impl.TalentServiceImpl;
import com.mangalaxy.mango.util.Samples;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TalentServiceTest {

  @Mock
  private TalentRepository talentRepository;

  private TalentService talentService;

  private Location location;
  private Talent talent1;
  private Talent talent2;
//  private Profile profile1;
//  private Profile profile2;

  @BeforeEach
  void setUp() {
    ModelMapper modelMapper = new ModelMapper();
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    talentService = new TalentServiceImpl(talentRepository, passwordEncoder, modelMapper);

    location = Samples.createLocation();

    talent1 = Talent.builder()
          .id(1L)
          .fullName("John Doe")
          .email("john.doe12@gmail.com")
          .password("#12hdk$573hdGH")
          .location(location)
          .build();

    talent2 = Talent.builder()
          .id(2L)
          .fullName("Thomas Miller")
          .email("t.miller@yahoo.com")
          .password("jsb2j4h3rbu4%")
          .location(location)
          .build();

    /*profile1 = Profile.builder()
          .id(talent1.getId())
          .owner(talent1)
          .preferredLocation(new Location("Boston", "USA"))
          .preferredSalary(new Salary("USD", BigDecimal.valueOf(150000L)))
          .preferredLanguages(Lists.newArrayList(new Language(Language.Level.FLUENT, "English")))
          .build();

    profile2 = Profile.builder()
          .id(talent2.getId())
          .owner(talent2)
          .preferredLocation(new Location("Los Angeles", "USA"))
          .preferredSalary(new Salary("USD", BigDecimal.valueOf(200000L)))
          .preferredLanguages(Lists.newArrayList(new Language(Language.Level.FLUENT, "English")))
          .build();*/

  }

  @Test
  void shouldFindFirstTalent_thenSuccess() {
    // given
    Long expectedId = 1L;
    when(talentRepository.findById(expectedId)).thenReturn(Optional.of(talent1));
    // when
    TalentResponse talentResponse = talentService.fetchTalentById(expectedId);
    // then
    verify(talentRepository).findById(expectedId);
    assertNotNull(talentResponse);
    assertEquals(expectedId, talentResponse.getId());
    assertEquals("john.doe12@gmail.com", talentResponse.getEmail());
  }

  @Test
  void shouldFindAllTalents_thenSuccess() {
    // given
    int expectedSize = 2;
    Pageable pageable = PageRequest.of(0, 20);
    Page<Talent> talentPage = new PageImpl<>(Lists.newArrayList(talent1, talent2));
    when(talentRepository.findAll(pageable)).thenReturn(talentPage);
    // when
    Page<TalentResponse> foundTalents = talentService.fetchTalentPage(pageable);
    // then
    verify(talentRepository).findAll(pageable);
    assertEquals(expectedSize, foundTalents.getSize());
    List<TalentResponse> content = foundTalents.getContent();
    assertEquals(1L, content.get(0).getId());
    assertEquals(2L, content.get(1).getId());
  }

  @Test
  void talentMustBeCreated_thenSuccess() {
    // given
    Long expectedId = 3L;
    LocationRequest location = new LocationRequest(
          Shorts.checkedCast(1L), "Toronto", "Canada");
    TalentSignUpRequest newTalent = TalentSignUpRequest.builder()
          .fullName("Jordan Enrich")
          .email("iam_rich@gmail.com")
          .password("83-def%24jdjK")
          .location(location)
          .build();
    when(talentRepository.save(any(Talent.class)))
          .thenReturn(Talent.builder()
                .id(3L)
                .fullName("Jordan Enrich")
                .email("iam_rich@gmail.com")
                .password("83-def%24jdjK")
                .build());
    // when
    TalentResponse talentResponse = talentService.createNewTalent(newTalent);
    // then
    verify(talentRepository).save(any(Talent.class));
    assertNotNull(talentResponse);
    assertEquals(expectedId, talentResponse.getId());
  }

  @Test
  void shouldUpdateTalentWithEmail_thenSuccess() {
    TalentUpdateRequest talentRequest = TalentUpdateRequest.builder()
          .email("john.doe_updated@gmail.com")
          .build();

    when(talentRepository.findById(anyLong())).thenReturn(Optional.of(talent1));
    when(talentRepository.save(any(Talent.class)))
          .thenReturn(Talent.builder()
                .id(1L)
                .fullName("John Doe")
                .email("john.doe_updated@gmail.com")
                .password("#12hdk$573hdGH")
                .location(location)
                .build());

    TalentResponse actual = talentService.updateTalentById(1L, talentRequest);

    verify(talentRepository).findById(1L);
    verify(talentRepository).save(any(Talent.class));
    assertThat(actual.getId()).isEqualTo(1L);
    assertThat(actual.getFullName()).isEqualTo("John Doe");
    assertThat(actual.getEmail()).isEqualTo("john.doe_updated@gmail.com");
  }

  @Test
  void talentMustBeDeleted_throwExceptionIfFail() {
    when(talentRepository.findById(1L)).thenReturn(Optional.of(talent1));
    talentService.deleteTalentById(1L);
    verify(talentRepository).findById(1L);
    verify(talentRepository).delete(talent1);
  }
}

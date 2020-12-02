package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.domain.entity.Language;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Profile;
import com.mangalaxy.mango.domain.entity.Salary;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.ProfileRepository;
import com.mangalaxy.mango.service.impl.ProfileServiceImpl;
import com.mangalaxy.mango.util.Samples;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
  @Mock
  private ProfileRepository profileRepository;

  private ProfileService profileService;

  private Profile profile;

  @BeforeEach
  public void setUp() {
    ModelMapper modelMapper = new ModelMapper();
    profileService = new ProfileServiceImpl(profileRepository, modelMapper);

    Location location = Samples.createLocation();

    Talent talent = Talent.builder()
          .id(1L)
          .fullName("John Doe")
          .email("john.doe12@gmail.com")
          .password("#12hdk$573hdGH")
          .location(location)
          .build();
    List<Language> languageList = Lists.newArrayList(new Language(Language.Level.FLUENT, "English"));
    profile = Profile.builder()
          .id(talent.getId())
          .owner(talent)
          .preferredLocation(new Location("Boston", "USA"))
          .preferredSalary(new Salary("USD", new BigDecimal(150000)))
          .preferredLanguages(languageList)
          .build();
  }

  @Test
  void shouldFindTalentProfile_thenSuccess() {
    // given
    Long expectedId = 1L;
    when(profileRepository.findById(anyLong())).thenReturn(Optional.of(profile));
    // when
    ProfileResponse profile = profileService.fetchTalentProfile(1L);
    // then
    verify(profileRepository).findById(anyLong());
    assertEquals(expectedId, profile.getId());
    assertEquals("John Doe", profile.getOwner().getFullName());
  }

}

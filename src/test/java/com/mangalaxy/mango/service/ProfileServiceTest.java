package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Profile;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.ProfileRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceTest {

  @MockBean
  private ProfileRepository profileRepository;

  @Autowired
  private ProfileService profileService;

  private Talent talent;
  private Profile profile;

  @Before
  public void setUp() {
    Location location = new Location();
    location.setId((short) 1);
    location.setCity("Austin");
    location.setCountry("USA");

    talent = new Talent();
    talent.setId(1L);
    talent.setEmail("test@gmai.com");
    talent.setPassword("123456");
    talent.setFullName("John Doe");
    talent.setLocation(location);

    profile = new Profile();
    profile.setId(1L);
    profile.setOwner(talent);
  }

  @Test
  public void getProfileTest() {
    Long expectedId = 1L;

    Mockito.when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));

    ProfileResponse profile = profileService.getProfileByTalent(1L);

    Assert.assertEquals(expectedId, profile.getId());
  }

}

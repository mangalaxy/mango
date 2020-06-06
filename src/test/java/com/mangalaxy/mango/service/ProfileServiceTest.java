package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Profile;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.ProfileRepository;
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

import java.util.Optional;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceTest {

  @MockBean
  private ProfileRepository profileRepository;

  @Autowired
  private ProfileService profileService;

  private final Talent firstMockTalent = new Talent();
  private final Profile mockProfile = new Profile();

  @Before
  public void setUp() {
    Location location = new Location();
    location.setId((short) 1);
    location.setCity("Austin");
    location.setCountry("USA");

    firstMockTalent.setId(1L);
    firstMockTalent.setEmail("test@gmai.com");
    firstMockTalent.setPassword("123456");
    firstMockTalent.setFullName("John Doe");
    firstMockTalent.setLocation(location);

    mockProfile.setId(1L);
    mockProfile.setOwner(firstMockTalent);
  }

  @Test
  public void getProfileTest() {
    Long expectedId = 1L;

    Mockito.when(profileRepository.findById(1L)).thenReturn(Optional.of(mockProfile));

    ProfileResponse profile = profileService.getProfileByTalent(1L);

    Assert.assertEquals(expectedId, profile.getId());
  }

}

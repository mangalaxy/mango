package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.entity.Location;
import com.mangalaxy.mango.model.entity.Profile;
import com.mangalaxy.mango.model.entity.Talent;
import com.mangalaxy.mango.repository.ProfileRepository;
import com.mangalaxy.mango.repository.TalentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TalentServiceTest {

  @Autowired
  private TalentService talentService;

  @MockBean
  private TalentRepository talentRepository;

  @MockBean
  private ProfileRepository profileRepository;

  private static Talent firstMockTalent;
  private static Talent secondMockTalent;
  private static Profile mockProfile;

  @Before
  public void initialization() {
    Talent firstTalent = new Talent();
    Talent secondTalent = new Talent();

    Location location = new Location();
    location.setId(1L);
    location.setCity("Kyiv");
    location.setCountry("UA");

    Profile profile = new Profile();

    firstTalent.setId(1L);
    firstTalent.setEmail("test@gmai.com");
    firstTalent.setPassword("123456");
    firstTalent.setFullName("Ilon Mask");
    firstTalent.setLocation(location);

    secondTalent.setId(2L);
    secondTalent.setEmail("test2@gmai.com");
    secondTalent.setPassword("123456");
    secondTalent.setFullName("Leo Messi");
    secondTalent.setLocation(location);

    profile.setId(1L);
    profile.setOwner(firstTalent);

    firstMockTalent = firstTalent;
    secondMockTalent = secondTalent;
    mockProfile = profile;
  }

  @Test
  public void getTalentByIdTest() {
    Long expectedId = 1L;
    String expectedEmail = "test@gmai.com";

    Mockito.when(talentRepository.findById(expectedId)).thenReturn(Optional.of(firstMockTalent));
    Talent talent = talentService.getByid(expectedId);

    Assert.assertEquals(expectedEmail, talent.getEmail());
    Assert.assertEquals(expectedId, talent.getId());
  }

  @Test
  public void getAllTalentsTest() {
    int expectedSize = 2;

    List<Talent> talents = new ArrayList<>();
    talents.add(firstMockTalent);
    talents.add(secondMockTalent);

    Pageable pageable = mock(Pageable.class);

    Page<Talent> tallentsList = new  PageImpl(talents);

    Mockito.when(talentRepository.findAll(pageable)).thenReturn(tallentsList);

    Page<Talent> allTallents = talentService.findAll(pageable);

    Assert.assertEquals(expectedSize, allTallents.getContent().size());
  }

  @Test
  public void getProfileTest() {
    Long expectedId = 1L;

    Mockito.when(profileRepository.findByOwner(firstMockTalent)).thenReturn(mockProfile);
    Mockito.when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));

    Profile profile = talentService.getProfileByTalent(1L);

    Assert.assertEquals(expectedId, profile.getId());
  }

  @Test
  public void createTalentTest() {
    Talent talent = new Talent();

    talent.setId(3L);
    talent.setEmail("my@gmail.com");

    Mockito.when(talentRepository.save(talent)).thenReturn(talent);

    Talent createdTalent = talentService.create(talent);

    Assert.assertNotNull(createdTalent);
  }

  @Test
  public void updateTalentTest() {
    firstMockTalent.setEmail("new-mail@gmail.com");

    Mockito.when(talentRepository.save(firstMockTalent)).thenReturn(firstMockTalent);

    Talent updatedTalent = talentService.update(firstMockTalent, 1L);

    Assert.assertEquals("new-mail@gmail.com", updatedTalent.getEmail());
  }

  @Test
  public void deleteTalentTest() {
    when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));
    talentService.delete(1L);
    verify(talentRepository, times(1)).delete(firstMockTalent);
  }
}

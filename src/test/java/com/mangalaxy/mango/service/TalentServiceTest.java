package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.dto.request.TalentRequest;
import com.mangalaxy.mango.model.dto.response.TalentResponse;
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
import org.modelmapper.ModelMapper;
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

  @Autowired
  private ModelMapper modelMapper;

  @MockBean
  private TalentRepository talentRepository;

  private static Talent firstMockTalent = new Talent();
  private static Talent secondMockTalent = new Talent();

  @Before
  public void setUp() {

    Location location = new Location();
    location.setId(1L);
    location.setCity("Kyiv");
    location.setCountry("UA");

    Profile profile = new Profile();

    firstMockTalent.setId(1L);
    firstMockTalent.setEmail("test@gmai.com");
    firstMockTalent.setPassword("123456");
    firstMockTalent.setFullName("Ilon Mask");
    firstMockTalent.setLocation(location);

    secondMockTalent.setId(2L);
    secondMockTalent.setEmail("test2@gmai.com");
    secondMockTalent.setPassword("123456");
    secondMockTalent.setFullName("Leo Messi");
    secondMockTalent.setLocation(location);

    profile.setId(1L);
    profile.setOwner(firstMockTalent);

  }

  @Test
  public void getTalentByIdTest() {
    Long expectedId = 1L;
    String expectedEmail = "test@gmai.com";

    Mockito.when(talentRepository.findById(expectedId)).thenReturn(Optional.of(firstMockTalent));
    TalentResponse talent = talentService.getTalentById(expectedId);
    verify(talentRepository).findById(expectedId);

    Assert.assertEquals(expectedEmail, talent.getEmail());
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

    Page<TalentResponse> allTallents = talentService.findAll(pageable);
    verify(talentRepository).findAll(pageable);

    Assert.assertEquals(expectedSize, allTallents.getContent().size());
    Assert.assertEquals(firstMockTalent.getEmail(), allTallents.getContent().get(0).getEmail());
  }

  @Test
  public void createTalentTest() {
    Mockito.when(talentRepository.save(firstMockTalent)).thenReturn(firstMockTalent);
    TalentRequest talentRequest = modelMapper.map(firstMockTalent, TalentRequest.class);
    talentService.createNewTalent(talentRequest);
    verify(talentRepository).save(firstMockTalent);
  }

  @Test
  public void updateTalentTest() {
    firstMockTalent.setEmail("new-mail@gmail.com");
    TalentRequest talentRequest = modelMapper.map(firstMockTalent, TalentRequest.class);
    Mockito.when(talentRepository.save(firstMockTalent)).thenReturn(firstMockTalent);

    TalentResponse updatedTalent = talentService.updateTalent(talentRequest, 1L);

    verify(talentRepository).save(firstMockTalent);
    Assert.assertEquals("new-mail@gmail.com", updatedTalent.getEmail());
  }

  @Test
  public void deleteTalentTest() {
    when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));
    talentService.deleteTalent(1L);
    verify(talentRepository, times(1)).delete(firstMockTalent);
  }
}

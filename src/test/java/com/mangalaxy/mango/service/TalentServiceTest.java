package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Profile;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Ignore
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
    location.setId((short) 1);
    location.setCity("Frankfurt");
    location.setCountry("Germany");

    firstMockTalent = new Talent();
    firstMockTalent.setId(1L);
    firstMockTalent.setEmail("test@gmai.com");
    firstMockTalent.setPassword("123456");
    firstMockTalent.setFullName("Mark Schmidt");
    firstMockTalent.setLocation(location);

    secondMockTalent = new Talent();
    secondMockTalent.setId(2L);
    secondMockTalent.setEmail("test2@gmai.com");
    secondMockTalent.setPassword("123456");
    secondMockTalent.setFullName("Leo Miller");
    secondMockTalent.setLocation(location);

    Profile profile = new Profile();
    profile.setId(1L);
    profile.setOwner(firstMockTalent);

  }

  @Test
  public void getTalentByIdTest() throws ResourceNotFoundException {
    Long expectedId = 1L;
    String expectedEmail = "test@gmai.com";

    Mockito.when(talentRepository.findById(expectedId)).thenReturn(Optional.of(firstMockTalent));
    TalentResponse talent = talentService.getTalentById(expectedId);
    verify(talentRepository).findById(expectedId);

    Assert.assertEquals(expectedEmail, talent.getEmail());
  }

  @Test
  public void getAllTalentsTest() throws ResourceNotFoundException {
    int expectedSize = 2;

    List<Talent> talents = new ArrayList<>();
    talents.add(firstMockTalent);
    talents.add(secondMockTalent);

    Pageable pageable = mock(Pageable.class);

    Page<Talent> talentList = new PageImpl<>(talents);

    Mockito.when(talentRepository.findAll(pageable)).thenReturn(talentList);

    Page<TalentResponse> allTalents = talentService.findAll(pageable);
    verify(talentRepository).findAll(pageable);

    Assert.assertEquals(expectedSize, allTalents.getContent().size());
    Assert.assertEquals(firstMockTalent.getEmail(), allTalents.getContent().get(0).getEmail());
  }

  @Test
  public void createTalentTest() {
    Mockito.when(talentRepository.save(firstMockTalent)).thenReturn(firstMockTalent);
    LocationRequest locationRequest = LocationRequest.builder()
        .id((short) 1L)
        .country("UA")
        .city("Kyiv")
        .build();
    TalentRequest talentRequest = TalentRequest.builder()
        .id(1L)
        .password("123456")
        .email("test@gmai.com")
        .fullName("Ilon Mask")
        .location(locationRequest)
        .build();
    talentService.createNewTalent(talentRequest);
    verify(talentRepository).save(firstMockTalent);
  }

  @Test
  public void updateTalentTest() throws ResourceNotFoundException {
    firstMockTalent.setEmail("new-mail@gmail.com");
    LocationRequest locationRequest = LocationRequest.builder()
        .id((short) 1L)
        .country("UA")
        .city("Kyiv")
        .build();
    TalentRequest talentRequest = TalentRequest.builder()
        .id(1L)
        .password("123456")
        .email("new-mail@gmail.com")
        .fullName("Ilon Mask")
        .location(locationRequest)
        .build();

    Mockito.when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));
    Mockito.when(talentRepository.save(firstMockTalent)).thenReturn(firstMockTalent);

    TalentResponse updatedTalent = talentService.updateTalent(talentRequest, 1L);

    verify(talentRepository).save(firstMockTalent);
    Assert.assertEquals("new-mail@gmail.com", updatedTalent.getEmail());
  }

  @Test
  public void deleteTalentTest() throws ResourceNotFoundException {
    when(talentRepository.findById(1L)).thenReturn(Optional.of(firstMockTalent));
    talentService.deleteTalent(1L);
    verify(talentRepository, times(1)).delete(firstMockTalent);
  }
}

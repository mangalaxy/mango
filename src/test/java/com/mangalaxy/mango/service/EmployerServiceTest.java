package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.TalentRepository;
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
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployerServiceTest {

  @MockBean
  private EmployerRepository employerRepository;

  @MockBean
  private TalentRepository talentRepository;

  @Autowired
  private EmployerService employerService;

  @Autowired
  private ModelMapper modelMapper;

  private Employer firstMockEmployer = new Employer();
  private Employer secondMockEmployer = new Employer();
  private Employer thirdMockEmployer = new Employer();
  private Talent mockTalent = new Talent();

  @Before
  public void setUp() {
    Location firstLocation = new Location();
    firstLocation.setId((short)1);
    firstLocation.setCity("Kyiv");
    firstLocation.setCountry("UA");

    mockTalent.setId(1L);
    mockTalent.setEmail("test@gmai.com");
    mockTalent.setPassword("123456");
    mockTalent.setFullName("Ilon Mask");
    mockTalent.setLocation(firstLocation);

    Location secondLocation = new Location();
    secondLocation.setId((short)2);
    secondLocation.setCity("Lviv");
    secondLocation.setCountry("UA");

    firstMockEmployer.setId(1L);
    firstMockEmployer.setFullName("Elon Mask");
    firstMockEmployer.setEmail("elon@gmail.com");
    firstMockEmployer.setPassword("123456");
    firstMockEmployer.setLocation(firstLocation);

    secondMockEmployer.setId(2L);
    secondMockEmployer.setFullName("Mark Zuckerberg");
    secondMockEmployer.setEmail("mark@gmail.com");
    secondMockEmployer.setPassword("123456");
    secondMockEmployer.setLocation(firstLocation);

    thirdMockEmployer.setId(3L);
    thirdMockEmployer.setFullName("Bill Gates");
    thirdMockEmployer.setEmail("bill@gmail.com");
    thirdMockEmployer.setPassword("123456");
    thirdMockEmployer.setLocation(secondLocation);
  }

  @Test
  public void shouldFindEmployersByParams() {
    int expectedSize = 3;
    List<Employer> employers = new ArrayList<>();
    employers.add(firstMockEmployer);
    employers.add(secondMockEmployer);
    employers.add(thirdMockEmployer);

    Pageable pageable = mock(Pageable.class);

    Page<Employer> employersSet = new PageImpl<>(employers);
    Mockito.when(employerRepository.findAll(pageable)).thenReturn(employersSet);

    Page<EmployerResponse> response = employerService.getEmployersByParams(pageable);

    Assert.assertEquals(3, response.getContent().size());
  }

  @Test
  public void shouldFindEmployerById() {
    Long expectedId = 1L;
    String expectedMail = "elon@gmail.com";

    Mockito.when(employerRepository.findById(expectedId)).thenReturn(java.util.Optional.of(firstMockEmployer));

    EmployerResponse response = employerService.getEmployerById(expectedId);

    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals(expectedMail, response.getEmail());
  }

  @Test
  public void shouldCreateEmployer() {
    Long expectedId = 1L;
    String expectedMail = "elon@gmail.com";

    LocationRequest locationRequest = LocationRequest.builder()
        .id((short) 1L)
        .country("UA")
        .city("Kyiv")
        .build();
    EmployerRequest employerRequest = EmployerRequest.builder()
        .id(expectedId)
        .email(expectedMail)
        .password("123456")
        .fullName("Elon Mask")
        .location(locationRequest)
        .build();

    Mockito.when(employerRepository.save(firstMockEmployer)).thenReturn(firstMockEmployer);
    EmployerResponse response = employerService.createNewEmployer(employerRequest);

    verify(employerRepository).save(firstMockEmployer);
    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals(expectedMail, response.getEmail());
  }

  @Test
  public void shouldUpdateEmployer() {
    Long expectedId = 1L;
    String expectedMail = "changed@gmail.com";
    firstMockEmployer.setEmail(expectedMail);

    LocationRequest locationRequest = LocationRequest.builder()
        .id((short) 1)
        .country("UA")
        .city("Kyiv")
        .build();
    EmployerRequest employerRequest = EmployerRequest.builder()
        .id(expectedId)
        .email(expectedMail)
        .password("123456")
        .fullName("Elon Mask")
        .location(locationRequest)
        .build();

    Mockito.when(employerRepository.findById(expectedId)).thenReturn(java.util.Optional.of(firstMockEmployer));
    Mockito.when(employerRepository.save(firstMockEmployer)).thenReturn(firstMockEmployer);
    EmployerResponse response = employerService.updateEmployer(employerRequest, expectedId);

    verify(employerRepository).save(firstMockEmployer);
    verify(employerRepository).findById(expectedId);
  }

  @Test
  public void shouldDeleteEmployerTest() {
    Mockito.when(employerRepository.findById(1L)).thenReturn(java.util.Optional.of(firstMockEmployer));
    employerService.deleteEmployer(1L);
    verify(employerRepository).delete(firstMockEmployer);
  }

  @Test
  public void shouldMathTalentToEmployer() {
    int expectedSize = 1;

    Set<Talent> talents = firstMockEmployer.getBookmarkedTalents();
    talents.add(mockTalent);
    firstMockEmployer.setBookmarkedTalents(talents);
    Mockito.when(employerRepository.findById(1L)).thenReturn(java.util.Optional.of(firstMockEmployer));
    Mockito.when(talentRepository.findById(1L)).thenReturn(java.util.Optional.of(mockTalent));
    Mockito.when(employerRepository.save(firstMockEmployer)).thenReturn(firstMockEmployer);

    EmployerResponse response = employerService.matchTalentToEmployer(1L, 1L, true);

    Assert.assertEquals(expectedSize, response.getTalents().size());

  }
}

package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.EmployerRepository;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployerServiceTest {
  @MockBean
  private EmployerRepository employerRepository;

  @Autowired
  private EmployerService employerService;

  @Autowired
  private ModelMapper modelMapper;

  private Employer firstMockEmployer = new Employer();
  private Employer secondMockEmployer = new Employer();
  private Employer thirdMockEmployer = new Employer();

  @Before
  public void setUp() {
    Location firstLocation = new Location();
    firstLocation.setId(1);
    firstLocation.setCity("Kyiv");
    firstLocation.setCountry("UA");

    Location secondLocation = new Location();
    secondLocation.setId(2);
    secondLocation.setCity("Lviv");
    secondLocation.setCountry("UA");

    firstMockEmployer.setId(1L);
    firstMockEmployer.setFullName("Elon Mask");
    firstMockEmployer.setWorkEmail("elon@gmail.com");
    firstMockEmployer.setPassword("123456");
    firstMockEmployer.setLocation(firstLocation);

    secondMockEmployer.setId(2L);
    secondMockEmployer.setFullName("Mark Zuckerberg");
    secondMockEmployer.setWorkEmail("mark@gmail.com");
    secondMockEmployer.setPassword("123456");
    secondMockEmployer.setLocation(firstLocation);

    thirdMockEmployer.setId(3L);
    thirdMockEmployer.setFullName("Bill Gates");
    thirdMockEmployer.setWorkEmail("bill@gmail.com");
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
    Assert.assertEquals(expectedMail, response.getWorkEmail());
  }

  @Test
  public void shouldCreateEmployer() {
    Long expectedId = 1L;
    String expectedMail = "elon@gmail.com";
    EmployerRequest request = modelMapper.map(firstMockEmployer, EmployerRequest.class);

    Mockito.when(employerRepository.save(firstMockEmployer)).thenReturn(firstMockEmployer);
    EmployerResponse response = employerService.createNewEmployer(request);

    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals(expectedMail, response.getWorkEmail());
  }

  @Test
  public void shouldUpdateEmployer() {
    Long expectedId = 1L;
    String expectedMail = "changed@gmail.com";
    firstMockEmployer.setWorkEmail(expectedMail);

    EmployerRequest request = modelMapper.map(firstMockEmployer, EmployerRequest.class);

    Mockito.when(employerRepository.save(firstMockEmployer)).thenReturn(firstMockEmployer);
    EmployerResponse response = employerService.updateEmployer(request, expectedId);

    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals(expectedMail, response.getWorkEmail());
  }

  @Test
  public void shouldDeleteEmployerTest() {
    Mockito.when(employerRepository.findById(1L)).thenReturn(java.util.Optional.of(firstMockEmployer));
    employerService.deleteEmployer(1L);
    verify(employerRepository).delete(firstMockEmployer);
  }
}
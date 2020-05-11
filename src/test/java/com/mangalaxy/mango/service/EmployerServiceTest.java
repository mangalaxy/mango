package com.mangalaxy.mango.service;

import com.google.common.collect.Lists;
import com.google.common.primitives.Shorts;
import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.util.EmployerNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployerServiceTest {

  @Mock
  private EmployerRepository employerRepository;

  private EmployerService employerService;

  private Location location1;
  private Location location2;
  private Employer employer1;
  private Employer employer2;
  private Employer employer3;

  @Before
  public void setUp() {
    ModelMapper modelMapper = new ModelMapper();
    employerService = new EmployerServiceImpl(employerRepository, modelMapper);

    location1 = new Location();
    location1.setId(Shorts.checkedCast(1L));
    location1.setCity("Toronto");
    location1.setCountry("Canada");

    location2 = new Location();
    location2.setId(Shorts.checkedCast(2L));
    location2.setCity("Barcelona");
    location2.setCountry("Spain");

    employer1 = new Employer();
    employer1.setId(1L);
    employer1.setFullName("Cortney Swiss");
    employer1.setEmail("cort.sw@yahoo.com");
    employer1.setPassword("skAS%fj67se32388");
    employer1.setJobTitle("IT Acquisition recruiter");
    employer1.setLocation(location1);

    employer2 = new Employer();
    employer2.setId(2L);
    employer2.setFullName("Mark Darton");
    employer2.setEmail("mark12@gmail.com");
    employer2.setPassword("Jaj1138%4SNxsj");
    employer2.setLocation(location1);

    employer3 = new Employer();
    employer3.setId(3L);
    employer3.setFullName("Adelina Veliz");
    employer3.setEmail("velizAFA@gmail.com");
    employer3.setPassword("skd*8391dhjj56");
    employer3.setJobTitle("Staff Executive Manager");
    employer3.setLocation(location2);
  }

  @Test
  public void shouldFindEmployerFirstPage_thenSuccess() {
    // given
    Pageable pageable = PageRequest.of(0, 20);
    Page<Employer> employerPage = new PageImpl<>(Lists.newArrayList(employer1, employer2, employer3));
    when(employerRepository.findAll(any(Pageable.class))).thenReturn(employerPage);
    // when
    Page<EmployerResponse> page = employerService.fetchAllEmployers(pageable);
    // then
    assertTrue(page.hasContent(), "No content");
    assertEquals(1, page.getTotalPages());
    assertEquals(3L, page.getTotalElements());
    List<EmployerResponse> content = page.getContent();
    assertEquals(Long.valueOf(1), content.get(0).getId());
    assertEquals(Long.valueOf(2), content.get(1).getId());
    assertEquals(Long.valueOf(3), content.get(2).getId());
    verify(employerRepository).findAll(pageable);
  }

  @Test
  public void shouldFindEmployerById_thenSuccess() {
    // given
    Long id = 1L;
    EmployerResponse expected = new EmployerResponse(1L, "Cortney Swiss", "cort.sw@yahoo.com",
          null, null, "IT Acquisition recruiter", null, null,
          null);
    when(employerRepository.findById(id)).thenReturn(Optional.of(employer1));
    // when
    EmployerResponse actual = employerService.fetchEmployerById(id);
    // then
    assertNotNull(actual);
    assertEquals(expected, actual);
    verify(employerRepository).findById(id);
  }

  @Test
  public void whenEmployerNotFoundThrowException() {
    Long id = 1L;
    assertThrows(EmployerNotFoundException.class,
          () -> employerService.fetchEmployerById(id),
          "An exception was not thrown");
    verify(employerRepository).findById(anyLong());
  }


  @Test
  public void employerMustBeCreated_thenSuccess() {
    Long expectedId = 4L;

    LocationRequest locationRequest = new LocationRequest(
          Shorts.checkedCast(1L), "Toronto", "Canada");

    EmployerRequest newEmployer = EmployerRequest.builder()
          .fullName("Mark Ostich")
          .email("mark.ostich@gmail.com")
          .jobTitle("HR generalist")
          .location(locationRequest)
          .build();

    when(employerRepository.save(any(Employer.class)))
          .thenReturn(Employer.builder()
                .id(4L)
                .fullName("Mark Ostich")
                .password("Uedu$%12893hd")
                .email("mark.ostich@gmail.com")
                .jobTitle("HR generalist")
                .location(location1)
                .build());

    EmployerResponse response = employerService.createNewEmployer(newEmployer);

    Assert.assertEquals(expectedId, response.getId());
    Assert.assertEquals("mark.ostich@gmail.com", response.getEmail());
    Assert.assertEquals("Mark Ostich", response.getFullName());
    verify(employerRepository).save(any(Employer.class));
  }

  @Test
  public void shouldUpdateEmployerById_thenSuccess() {
    Long id = 3L;

    EmployerRequest employerRequest = EmployerRequest.builder()
          .fullName("Delfina Olmos")
          .email("velizAFA@gmail.com")
          .jobTitle("Staff Executive Manager")
          .location(new LocationRequest((short) 2, "Barcelona", "Spain"))
          .build();

    when(employerRepository.findById(3L)).thenReturn(Optional.of(employer3));
    when(employerRepository.save(any(Employer.class)))
          .thenReturn(Employer.builder()
                .id(id)
                .fullName("Delfina Olmos")
                .email("velizAFA@gmail.com")
                .password("skd*8391dhjj56")
                .jobTitle("Staff Executive Manager")
                .location(location2)
                .build());
    EmployerResponse actual = employerService.updateEmployer(id, employerRequest);

    assertThat(actual.getId()).isEqualTo(3L);
    assertThat(actual.getFullName()).isEqualTo("Delfina Olmos");
    assertThat(actual.getEmail()).isEqualTo("velizAFA@gmail.com");
    assertThat(actual.getJobTitle()).isEqualTo("Staff Executive Manager");

    verify(employerRepository).findById(id);
    verify(employerRepository).save(any(Employer.class));
  }

  @Test
  public void employerMustBeDeleted_throwExceptionIfFail() {
    when(employerRepository.findById(1L)).thenReturn(Optional.of(employer1));
    employerService.deleteEmployerById(1L);
    verify(employerRepository).findById(1L);
    verify(employerRepository).delete(any(Employer.class));
  }

}

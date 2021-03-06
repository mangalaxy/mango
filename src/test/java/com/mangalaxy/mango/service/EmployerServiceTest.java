package com.mangalaxy.mango.service;

import com.google.common.collect.Lists;
import com.google.common.primitives.Shorts;
import com.mangalaxy.mango.domain.dto.request.EmployerSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.EmployerUpdateRequest;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.repository.CompanyRepository;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.service.impl.EmployerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployerServiceTest {

  @Mock
  private EmployerRepository employerRepository;
  @Mock
  private CompanyRepository companyRepository;
  private EmployerService employerService;

  private Location location1;
  private Location location2;
  private Employer employer1;
  private Employer employer2;
  private Employer employer3;
  private Company company1;

  @BeforeEach
  void setUp() {
    ModelMapper modelMapper = new ModelMapper();
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    modelMapper.getConfiguration()
          .setSourceNamingConvention(NamingConventions.NONE)
          .setDestinationNamingConvention(NamingConventions.NONE);
    employerService =  new EmployerServiceImpl(employerRepository, companyRepository, passwordEncoder, modelMapper);

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
    employer2.setPassword("Jaj1138%4SNxj");
    employer2.setLocation(location1);

    employer3 = new Employer();
    employer3.setId(3L);
    employer3.setFullName("Adelina Volta");
    employer3.setEmail("velizAFA@gmail.com");
    employer3.setPassword("skd*8391dhj56");
    employer3.setJobTitle("VP of Talent Acquisition");
    employer3.setLocation(location2);

    company1 = new Company();
    company1.setId(1L);
    company1.setName("Microsoft Inc.");
  }

  @Test
  @DisplayName("Find a page of first three employers")
  void shouldFindEmployerFirstPage_thenSuccess() {
    // given
    Pageable pageable = PageRequest.of(0, 20);
    Page<Employer> employerPage = new PageImpl<>(Lists.newArrayList(employer1, employer2, employer3));
    when(employerRepository.findAll(pageable)).thenReturn(employerPage);
    // when
    Page<EmployerResponse> page = employerService.fetchAllEmployers(pageable);
    // then
    assertTrue(page.hasContent(), "No content");
    assertEquals(1, page.getTotalPages());
    assertEquals(3L, page.getTotalElements());
    List<EmployerResponse> content = page.getContent();
    assertEquals(1L, content.get(0).getId());
    assertEquals(2L, content.get(1).getId());
    assertEquals(3L, content.get(2).getId());
    verify(employerRepository).findAll(pageable);
  }

  @Test
  void shouldFindEmployerById_thenSuccess() {
    // given
    Long id = 1L;
    EmployerResponse expected = EmployerResponse.builder()
          .id(1L)
          .fullName("Cortney Swiss")
          .email("cort.sw@yahoo.com")
          .jobTitle("IT Acquisition recruiter")
          .build();
    when(employerRepository.findById(id)).thenReturn(Optional.of(employer1));
    // when
    EmployerResponse actual = employerService.fetchEmployerById(id);
    // then
    assertNotNull(actual);
    assertEquals(expected, actual);
    verify(employerRepository).findById(id);
  }

  @Test
  void whenEmployerNotFoundThrowException() {
    Long id = 1L;
    assertThrows(ResourceNotFoundException.class,
          () -> employerService.fetchEmployerById(id),"An exception was not thrown");
    verify(employerRepository).findById(anyLong());
  }


  @Test
  void employerMustBeCreated_thenSuccess() {
    Long expectedId = 4L;

    LocationRequest locationRequest = new LocationRequest(
          Shorts.checkedCast(1L), "Toronto", "Canada");

    EmployerSignUpRequest newEmployer = EmployerSignUpRequest.builder()
          .fullName("Mark Finch")
          .email("mark.finch@gmail.com")
          .password("jedBU27*90")
          .jobTitle("HR Generalist")
          .companyName("Microsoft Inc.")
          .location(locationRequest)
          .build();

    Employer mockEmployer = Employer.builder()
          .id(4L)
          .fullName("Mark Finch")
          .password("Ue$%12893hd")
          .email("mark.finch@gmail.com")
          .jobTitle("HR Generalist")
          .location(location1)
          .company(company1)
          .build();
    when(employerRepository.save(any(Employer.class))).thenReturn(mockEmployer);
    EmployerResponse employerResponse = employerService.createNewEmployer(newEmployer);

    assertEquals(expectedId, employerResponse.getId());
    assertEquals("mark.finch@gmail.com", employerResponse.getEmail());
    assertEquals("Mark Finch", employerResponse.getFullName());
    assertEquals("Microsoft Inc.", employerResponse.getCompanyName());
    verify(companyRepository).findByNameIgnoreCase(anyString());
    verify(employerRepository).save(any(Employer.class));
  }

  @Test
  void shouldUpdateEmployerById_thenSuccess() {
    Long id = 3L;

    EmployerUpdateRequest employerRequest = EmployerUpdateRequest.builder()
          .fullName("Delfina Zara")
          .email("zara@gmail.com")
          .jobTitle("Staff Executive Manager")
          .location(new LocationRequest((short) 2, "Barcelona", "Spain"))
          .build();
    Employer mockEmployer = Employer.builder()
          .id(id)
          .fullName("Delfina Zara")
          .email("zara@gmail.com")
          .password("skd*8391dhj56")
          .jobTitle("Staff Executive Manager")
          .location(location2)
          .build();
    when(employerRepository.findById(id)).thenReturn(Optional.of(employer3));
    when(employerRepository.save(any(Employer.class))).thenReturn(mockEmployer);
    EmployerResponse actual = employerService.updateEmployerById(id, employerRequest);

    assertThat(actual.getId()).isEqualTo(3L);
    assertThat(actual.getFullName()).isEqualTo("Delfina Zara");
    assertThat(actual.getEmail()).isEqualTo("zara@gmail.com");
    assertThat(actual.getJobTitle()).isEqualTo("Staff Executive Manager");

    verify(employerRepository).findById(id);
    verify(employerRepository).save(any(Employer.class));
  }

  @Test
  void employerMustBeDeleted_throwExceptionIfFail() {
    when(employerRepository.findById(1L)).thenReturn(Optional.of(employer1));
    employerService.deleteEmployerById(1L);
    verify(employerRepository).findById(1L);
    verify(employerRepository).delete(any(Employer.class));
  }

}

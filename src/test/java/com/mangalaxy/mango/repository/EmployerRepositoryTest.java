package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.exception.EmployerNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EmployerRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private EmployerRepository employerRepository;

  @BeforeEach
  void setUp() {
    Location berlin = new Location("Berlin", "Germany");
    entityManager.persistAndFlush(berlin);

    Employer employer = new Employer();
    employer.setFullName("Anna Fisher");
    employer.setEmail("anna.fisher2019@gmail.com");
    employer.setPassword("123AK72retry");
    employer.setJobTitle("IT Executive Search Specialist");
    employer.setPhoneNumber("+49-89-636-48018");
    employer.setLocation(berlin);
    employer.setCreatedDate(LocalDateTime.now());

    Company company = new Company();
    company.setName("Siemens");
    company.getEmployers().add(employer);
    company.setCreatedDate(LocalDateTime.now());
    employer.setCompany(company);
    entityManager.persistAndFlush(employer);
  }

  @AfterEach
  void tearDown() {
    entityManager.clear();
  }

  @Test
  @DisplayName("Find an employer by email")
  void shouldFindEmployerByEmail_thenSuccess() {
    // given
    String email = "anna.fisher2019@gmail.com";
    // when
    Employer employer = employerRepository.findByEmail(email).orElseThrow(EmployerNotFoundException::new);
    // then
   assertEquals(email, employer.getEmail());
  }
}

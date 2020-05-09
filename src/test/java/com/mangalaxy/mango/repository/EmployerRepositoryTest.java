package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.util.EmployerNotFoundExeption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployerRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private EmployerRepository employerRepository;

  @Before
  public void setUp() {
    // given
    Location berlin = new Location("Berlin", "Germany");
    entityManager.persistAndFlush(berlin);

    Employer employer = new Employer();
    employer.setFullName("Anna Fisher");
    employer.setEmail("anna.fisher2019@gmail.com");
    employer.setPassword("123AKYGCV72rett");
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

  @Test
  public void shouldFindEmployerByEmail_thenSuccess() {
    // given
    String emailAddress = "anna.fisher2019@gmail.com";
    // when
    Employer employer = employerRepository.findByEmail(emailAddress)
          .orElseThrow(EmployerNotFoundExeption::new);
    // then
   assertThat(employer.getEmail()).isEqualTo(emailAddress);
  }
}

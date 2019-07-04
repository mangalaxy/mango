package com.mangalaxy.mango;

import com.mangalaxy.mango.model.entity.Company;
import com.mangalaxy.mango.model.entity.Employer;
import com.mangalaxy.mango.model.entity.Location;
import com.mangalaxy.mango.repository.EmployerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class EmployerTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private EmployerRepository employerRepository;

  @Before
  public void setUp() {
    // given
    Employer employer = new Employer();
    employer.setFullName("Anna Fisher");
    employer.setWorkEmail("anna.fisher2019@gmail.com");
    employer.setPassword("123AKYGCV72rett");
    employer.setJobTitle("IT Executive Search Specialist");
    employer.setPhoneNumber("+49-89-636-48018");
    employer.setLocation(new Location("Berlin", "Germany"));
    Company company = new Company();
    company.setName("Company-1");
    employer.setCompany(company);
    testEntityManager.persistAndFlush(employer);
  }

  @Test
  public void shouldFindEmployerByEmail_thenSuccess() {
    // given
    String emailAddress = "anna.fisher2019@gmail.com";
    // when
    Employer employer = employerRepository.findByWorkEmail(emailAddress).get();

    // then
    assertThat(employer.getWorkEmail()).isEqualTo(emailAddress);
  }
}

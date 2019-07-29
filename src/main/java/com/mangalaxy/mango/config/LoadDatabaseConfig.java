package com.mangalaxy.mango.config;

import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Slf4j
@Configuration
public class LoadDatabaseConfig {

  @Bean
  CommandLineRunner dataLoader(CompanyRepository repository) {
    return args -> {
      log.info("Start loading entities to database");
      Company company1 = Company.builder()
            .name("Dragon Innovation")
            .industry("Hardware")
            .headline("One of the most innovative company providing hard drive components.")
            .build();
      Company company2 = Company.builder()
            .name("Canistor")
            .industry("Agriculture")
            .size("100-200 employees")
            .build();
      log.info("Saved entity: {}", repository.save(company1));
      log.info("Saved entity: {}", repository.save(company2));
    };
  }

  @Bean
  CommandLineRunner dataChecker(CompanyRepository repository) {
    return args -> {
      log.info("Show {} saved companies in persistence context", repository.count());
      repository.findAll().forEach(c -> log.info("Fetched company with id: {} and name: {}", c.getId(), c.getName()));
      repository.findById(1L).ifPresent(company -> {
        company.setAddress("Austin, Texas, 124ADC, Hudson avenue");
        Company updatedCompany = repository.save(company);
        log.info("Changed entity with id: {}, name: {}, lastModifiedDate: {}",
              updatedCompany.getId(),
              updatedCompany.getName(),
              updatedCompany.getLastModifiedDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
      });

    };
  }
}

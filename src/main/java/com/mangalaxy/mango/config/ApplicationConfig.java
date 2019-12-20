package com.mangalaxy.mango.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(modifyOnCreate = false)
public class ApplicationConfig {

  @Bean(name = "dtoMapper")
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}

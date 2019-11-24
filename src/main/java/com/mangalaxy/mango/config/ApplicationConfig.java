package com.mangalaxy.mango.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(modifyOnCreate = false)
@EnableSwagger2
public class ApplicationConfig {

  @Bean(name = "dtoMapper")
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build();
  }

}

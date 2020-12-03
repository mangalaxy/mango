package com.mangalaxy.mango.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI mangostartOpenAPI() {
    return new OpenAPI()
          .info(new Info()
                .title("MANGOSTART API")
                .description("REST API for MGS recruiting platform")
                .version("v1.0.0")
                .license(new License()
                      .name("MIT License")
                      .url("https://opensource.org/licenses/MIT")))
          .externalDocs(new ExternalDocumentation()
                .description("MANGOSTART Wiki Documentation")
                .url("https://springshop.wiki.github.org/docs"));
  }

}

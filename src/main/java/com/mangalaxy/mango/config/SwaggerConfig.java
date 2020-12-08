package com.mangalaxy.mango.config;

import com.google.common.collect.Sets;
import com.mangalaxy.mango.security.CurrentUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.ant;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

  @Bean
  public Docket mangoApi() {
    return new Docket(DocumentationType.SWAGGER_2)
          .ignoredParameterTypes(CurrentUser.class)
          .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
          .select()
          .apis(basePackage("com.mangalaxy.mango.controller"))
          .paths(ant("/api/**"))
          .build()
          .apiInfo(metaData());
  }

  private static ApiInfo metaData() {
    return new ApiInfoBuilder()
          .title("MANGOSTART REST API")
          .description("REST API for ManGoStart recruiting platform")
          .version("1.0.0-SNAPSHOT")
          .license("MIT License")
          .licenseUrl("https://opensource.org/licenses/MIT")
          .build();
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
          .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}

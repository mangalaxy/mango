package com.mangalaxy.mango;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MangoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MangoApplication.class, args);
  }

}

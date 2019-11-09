package com.mangalaxy.mango.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Profile("test")
@Configuration
public class ServiseTestConfigiration {

  @Bean
  @Primary
  public JavaMailSender createMailSender() {
    return new JavaMailSenderImpl();
  }
}

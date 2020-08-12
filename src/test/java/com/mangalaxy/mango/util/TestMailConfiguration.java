package com.mangalaxy.mango.util;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@TestConfiguration
public class TestMailConfiguration {

  @Bean
  @Primary
  public JavaMailSender mailSender() {
    return new JavaMailSenderImpl();
  }
}

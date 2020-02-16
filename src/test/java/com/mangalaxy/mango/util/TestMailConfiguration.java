package com.mangalaxy.mango.util;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Profile("test")
@TestConfiguration
public class TestMailConfiguration {

  @Bean("mailSender")
  @Primary
  public JavaMailSender createMailSender() {
    return new JavaMailSenderImpl();
  }
}

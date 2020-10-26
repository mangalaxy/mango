package com.mangalaxy.mango.util;

import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.service.CustomUserDetailsService;
import com.mangalaxy.mango.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

//@Component
@RequiredArgsConstructor
public class RegistrationListener implements
    ApplicationListener<OnRegistrationCompleteEvent> {
  private final CustomUserDetailsService service;
  private final MailSenderService mailSenderService;

  @Value("${app.domain}")
  private String domain;

  @Override
  public void onApplicationEvent(OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(OnRegistrationCompleteEvent event) {
    User user = event.getUser();
    String token = UUID.randomUUID().toString();
    service.createVerificationToken(user, token);

    String recipientAddress = user.getEmail();
    String subject = "Registration Confirmation";
    String confirmationUrl = event.getAppUrl() + "/api/v1/auth/confirm?token=" + token;

    mailSenderService.send(domain + confirmationUrl, subject, recipientAddress);

  }
}

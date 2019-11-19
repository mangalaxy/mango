package com.mangalaxy.mango.util;

import com.mangalaxy.mango.domain.entity.User;
import com.mangalaxy.mango.service.CustomUserDetailsService;
import com.mangalaxy.mango.service.MailSenderServise;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements
    ApplicationListener<OnRegistrationCompleteEvent> {
  private final CustomUserDetailsService service;
  private final MailSenderServise mailSenderServise;

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
    String confirmationUrl
        = event.getAppUrl() + "/api/v1/auth/regitrationConfirm?token=" + token;

    mailSenderServise.send(domain + confirmationUrl, subject, recipientAddress);

  }
}

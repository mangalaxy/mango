package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RequiredArgsConstructor
//@Service
public class MailSenderImpl implements MailSenderService {

  private final JavaMailSender javaMailSender;

  @Override
  public void send(String text, String subject, String toEmail) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setText(text);
    message.setSubject(subject);
    message.setTo(toEmail);

    javaMailSender.send(message);
  }
}

package com.mangalaxy.mango.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderImpl implements MailSenderServise{

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

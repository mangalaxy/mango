package com.mangalaxy.mango.service;

import com.mangalaxy.mango.util.SmtpServerRule;
import org.junit.Rule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = { MailSenderImpl.class, JavaMailSenderImpl.class})
class MailSenderServiceTest {

  @Autowired
  private MailSenderService mailSenderService;

  @Rule
  public SmtpServerRule smtpServerRule;

  @Disabled("Doesn't work because mail server not configured")
  @Test
  void shouldSendMailTest() throws MessagingException, IOException {
    String text = "Test message";
    String mailTo = "nikolai.blashchuk@gmail.com";
    String subject = "Test Mail";
    mailSenderService.send(text, subject, mailTo);

    MimeMessage[] receivedMessages = smtpServerRule.getMessages();
    assertEquals(1, receivedMessages.length);

    MimeMessage current = receivedMessages[0];

    assertEquals(subject, current.getSubject());
    assertEquals(mailTo, current.getAllRecipients()[0].toString());
    assertTrue(String.valueOf(current.getContent()).contains(text));
  }
}


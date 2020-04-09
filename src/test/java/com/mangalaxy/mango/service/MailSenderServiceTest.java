package com.mangalaxy.mango.service;

import com.mangalaxy.mango.util.SmtpServerRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class MailSenderServiceTest {

  @Autowired
  private MailSenderService mailSenderService;

  @Rule
  public SmtpServerRule smtpServerRule;

  @Test
  public void shouldSendMailTest() throws MessagingException, IOException {
    String text = "Test";
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

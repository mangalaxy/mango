package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.service.MailSenderServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/mail")
public class MailController {
  @Autowired
  private MailSenderServise mailSenderServise;

  @PostMapping
  public void sendMail(@RequestBody String text) {
    mailSenderServise.send(text, "Test Mail", "nikolai.blashchuk@gmail.com");
  }

}

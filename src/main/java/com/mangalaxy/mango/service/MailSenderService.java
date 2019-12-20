package com.mangalaxy.mango.service;

public interface MailSenderService {

  void send(String message, String subject, String email);

}

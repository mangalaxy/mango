package com.mangalaxy.mango.util;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.rules.ExternalResource;

import javax.mail.internet.MimeMessage;

public class SmtpServerRule extends ExternalResource {

  private GreenMail mailServer;

  @Override
  protected void before() throws Throwable {
    super.before();
    ServerSetup setupTest = ServerSetupTest.SMTP;
    mailServer = new GreenMail(setupTest);
    mailServer.start();
  }

  public MimeMessage[] getMessages() {
    return mailServer.getReceivedMessages();
  }

  @Override
  protected void after() {
    super.after();
    mailServer.stop();
  }
}

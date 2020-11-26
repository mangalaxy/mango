package com.mangalaxy.mango.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.net.URL;
import java.util.Locale;

@Getter
@Setter
public class ConfirmRegistrationEvent extends ApplicationEvent {
  private URL confirmationUrl;
  private Locale locale;
  private Long clientId;

  public ConfirmRegistrationEvent(Long clientId, URL confirmationUrl) {
    super(clientId);
    this.confirmationUrl = confirmationUrl;
  }

}
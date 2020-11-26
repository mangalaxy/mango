package com.mangalaxy.mango.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<ConfirmRegistrationEvent> {

  @Override
  public void onApplicationEvent(ConfirmRegistrationEvent onRegistrationCompleteEvent) {
    // TODO: Handle event properly
  }

}

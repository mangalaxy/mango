package com.mangalaxy.mango;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Only for testing rest controllers
 */
@RestController
public class HelloController {

  @GetMapping("/api/v1/hello")
  public String hello() {
    return "Hello there";
  }
}
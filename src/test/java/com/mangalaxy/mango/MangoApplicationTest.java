package com.mangalaxy.mango;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * Integration test for Spring Boot Web.
 *
 * @author Yuri Podolsky
 */
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MangoApplicationTest {

  @LocalServerPort
  private int localServerPort;

  @Test
  @Disabled("Doesn't work due to circular dependency, fix it later")
  void contextLoads() {
  }

}
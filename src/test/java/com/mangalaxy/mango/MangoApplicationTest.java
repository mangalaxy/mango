package com.mangalaxy.mango;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * Integration test for Spring Boot Web.
 *
 * @author Yuri Podolsky
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MangoApplicationTest {

  @LocalServerPort
  private int localServerPort;

  @Test
  public void contextLoads() {
  }

}
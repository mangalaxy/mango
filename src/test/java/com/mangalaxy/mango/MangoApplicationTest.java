package com.mangalaxy.mango;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Integration test for Spring Boot Web.
 *
 * @author Yuri Podolsky
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MangoApplicationTest {

  @Value("${local.server.port}")
  private int localServerPort;

  @Test
  public void contextLoads() {
  }

}
package com.mangalaxy.mango;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for the whole application.
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

  @Test
  public void shouldSayHello() {

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate
          .getForEntity("http://localhost:{port}/api/v1/hello", String.class, localServerPort);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("Hello there");
  }
}
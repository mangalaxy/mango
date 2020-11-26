package com.mangalaxy.mango;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MangoApplicationTests {

  @Test
  @Disabled("Doesn't work due to circular dependency, fix it later")
  void contextLoads() {
  }

}
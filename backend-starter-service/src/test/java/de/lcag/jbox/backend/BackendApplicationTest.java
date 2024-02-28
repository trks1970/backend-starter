package de.lcag.jbox.backend;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class BackendApplicationTest {

  @Test
  void testContextLoads() {
    Assertions.assertThat(this.getClass()).isEqualTo(BackendApplicationTest.class);
  }
}

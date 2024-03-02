package de.lcag.jbox.backend;

import de.lcag.jbox.backend.testsupport.DropCreateJpaTestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {DropCreateJpaTestConfig.class, LiquibaseAutoConfiguration.class})
@DirtiesContext
class BackendApplicationTest {

  @Test
  void testContextLoads() {
    Assertions.assertThat(this.getClass()).isEqualTo(BackendApplicationTest.class);
  }
}

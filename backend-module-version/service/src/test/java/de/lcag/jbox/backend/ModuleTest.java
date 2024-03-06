package de.lcag.jbox.backend;

import de.lcag.jbox.backend.config.VersionConfig;
import de.lcag.jbox.backend.testsupport.DropCreateJpaTestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@Import(VersionConfig.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {DropCreateJpaTestConfig.class})
@DirtiesContext
class ModuleTest {

  @Test
  void testContextLoads() {
    Assertions.assertThat(this.getClass()).isEqualTo(ModuleTest.class);
  }
}

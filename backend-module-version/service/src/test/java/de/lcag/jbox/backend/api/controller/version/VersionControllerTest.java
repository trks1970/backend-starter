package de.lcag.jbox.backend.api.controller.version;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.lcag.jbox.backend.testsupport.ModuleTestApplication;
import de.lcag.jbox.backend.testsupport.DropCreateJpaTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(
    classes = {ModuleTestApplication.class, DropCreateJpaTestConfig.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
public class VersionControllerTest {

  @LocalServerPort protected int port;

  @Value("${api.base-path:}")
  protected String contextName;

  @Autowired @Nullable MockMvc mvc;

  @Test
  void getVersion() throws Exception {
    // given
    assert mvc != null;
    String url4Profile = String.format("http://localhost:%s/%s/version", port, contextName);

    // when/then
    mvc.perform(get(url4Profile))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.build", is("local")));
  }
}

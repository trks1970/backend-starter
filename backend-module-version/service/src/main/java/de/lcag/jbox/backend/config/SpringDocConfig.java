package de.lcag.jbox.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringDocConfig {
  private final String applicationName;
  private final String applicationVersion;

  @Bean
  public OpenAPI applicationApi() {
    return new OpenAPI().info(new Info().title(applicationName).version(applicationVersion));
  }
}

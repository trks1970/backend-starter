package de.lcag.jbox.backend.testsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "de.lcag.jbox")
@EnableConfigurationProperties
public class ModuleTestApplication {
  public static void main(String[] args) {
    SpringApplication.run(ModuleTestApplication.class, args);
  }
}

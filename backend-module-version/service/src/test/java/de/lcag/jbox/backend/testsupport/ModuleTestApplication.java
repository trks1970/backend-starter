package de.lcag.jbox.backend;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "de.lcag.jbox")
@EnableConfigurationProperties
public class ModuleTestApplication {
  @Generated // exclude from jacoco code coverage
  public static void main(String[] args) {
    SpringApplication.run(ModuleTestApplication.class, args);
  }
}

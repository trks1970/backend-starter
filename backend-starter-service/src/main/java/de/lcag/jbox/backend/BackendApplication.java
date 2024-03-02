package de.lcag.jbox.backend;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BackendApplication {
  @Generated // exclude from jacoco code coverage
  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }
}

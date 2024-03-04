package de.lcag.jbox.backend.config;

import de.lcag.jbox.backend.domain.model.version.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class VersionConfig implements InitializingBean {
  private static final Integer MAJOR = 0;
  private static final Integer MINOR = 1;
  private static final Integer PATCH = 2;

  @Value("${spring.application.name}")
  private String applicationName;

  @Value("${spring.application.version}")
  private String applicationVersion;

  @Bean
  public Version version() {
    String[] splitVersion = applicationVersion.split("-");
    String[] semVer = splitVersion[0].split("\\.");
    return new Version(
        Integer.valueOf(semVer[MAJOR]),
        Integer.valueOf(semVer[MINOR]),
        Integer.valueOf(semVer[PATCH]),
        splitVersion[1]);
  }

  @Bean
  public String applicationVersion() {
    return applicationVersion;
  }

  @Bean
  public String applicationName() {
    return applicationName;
  }

  @Override
  public void afterPropertiesSet() {
    log.info(
        "Application {} version {} running on Java {}",
        applicationName,
        applicationVersion,
        System.getProperty("java.version"));
  }
}

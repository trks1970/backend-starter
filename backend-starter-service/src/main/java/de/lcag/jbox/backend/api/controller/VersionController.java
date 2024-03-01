package de.lcag.jbox.backend.api.controller;

import de.lcag.jbox.backend.api.VersionApi;
import de.lcag.jbox.backend.api.mapper.VersionMapper;
import de.lcag.jbox.backend.api.resource.VersionResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.base-path:}")
@Slf4j
public class VersionController implements VersionApi, InitializingBean {
  private final VersionMapper versionMapper;

  @Value("${spring.application.name}")
  private String applicationName;

  @Value("${spring.application.version}")
  private String applicationVersion;

  @Override
  public ResponseEntity<VersionResource> getVersion() {
    return ResponseEntity.ok(versionMapper.toVersion(applicationVersion));
  }

  @Override
  public void afterPropertiesSet() {
    System.out.println("afterPropertiesSet " + applicationName + " " + applicationVersion);
    log.info(
        "Application {} version {} running on Java {}",
        applicationName,
        applicationVersion,
        System.getProperty("java.version"));
  }
}

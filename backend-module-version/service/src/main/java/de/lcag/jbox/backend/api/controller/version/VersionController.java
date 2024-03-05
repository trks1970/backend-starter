package de.lcag.jbox.backend.api.controller.version;

import de.lcag.jbox.backend.api.VersionApi;
import de.lcag.jbox.backend.api.mapper.version.VersionMapper;
import de.lcag.jbox.backend.api.resource.VersionResource;
import de.lcag.jbox.backend.domain.service.version.query.VersionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.base-path:}")
@Slf4j
public class VersionController implements VersionApi {
  private final VersionQueryService versionQueryService;
  private final VersionMapper versionMapper;

  @Override
  public ResponseEntity<VersionResource> getVersion() {

    return ResponseEntity.ok(versionMapper.toResource(versionQueryService.getVersion()));
  }
}

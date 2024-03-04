package de.lcag.jbox.backend.domain.service.version;

import de.lcag.jbox.backend.domain.model.version.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VersionService {
  private final Version version;

  public Version getVersion() {
    return version;
  }
}

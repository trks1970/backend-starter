package de.lcag.jbox.backend.domain.service.version.query;

import de.lcag.jbox.backend.domain.model.version.Version;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor
@Slf4j
public class VersionQueryService {
  private final Version version;
}

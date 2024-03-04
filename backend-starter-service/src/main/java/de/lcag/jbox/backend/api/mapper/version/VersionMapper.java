package de.lcag.jbox.backend.api.mapper.version;

import de.lcag.jbox.backend.api.resource.VersionResource;
import de.lcag.jbox.backend.domain.model.version.Version;
import org.mapstruct.Mapper;

@Mapper
public interface VersionMapper {
  VersionResource toResource(Version version);
}

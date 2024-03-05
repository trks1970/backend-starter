package de.lcag.jbox.backend.api.mapper.query;

import de.lcag.jbox.backend.api.resource.PageRequestResource;
import de.lcag.jbox.backend.domain.model.query.PageRequest;
import org.mapstruct.Mapper;

@Mapper
public interface PageRequestMapper {
  PageRequest toDomain(PageRequestResource pageRequestResource);
}

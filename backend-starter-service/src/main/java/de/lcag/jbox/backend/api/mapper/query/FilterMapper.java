package de.lcag.jbox.backend.api.mapper.query;

import de.lcag.jbox.backend.api.resource.FilterResource;
import de.lcag.jbox.backend.domain.model.query.Filter;
import org.mapstruct.Mapper;

@Mapper
public interface FilterMapper {
  Filter toDomain(FilterResource filterResource);
}

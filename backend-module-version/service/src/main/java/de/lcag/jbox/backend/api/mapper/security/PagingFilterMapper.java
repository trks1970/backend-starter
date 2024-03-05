package de.lcag.jbox.backend.api.mapper.security;

import de.lcag.jbox.backend.api.mapper.query.FilterMapper;
import de.lcag.jbox.backend.api.mapper.query.PageRequestMapper;
import de.lcag.jbox.backend.api.mapper.query.SortRequestMapper;
import de.lcag.jbox.backend.api.resource.PagingFilterResource;
import de.lcag.jbox.backend.domain.model.query.PagingFilter;
import org.mapstruct.Mapper;

@Mapper(uses = {FilterMapper.class, PageRequestMapper.class, SortRequestMapper.class})
public interface PagingFilterMapper {
  PagingFilter toDomain(PagingFilterResource pagingFilterResource);
}

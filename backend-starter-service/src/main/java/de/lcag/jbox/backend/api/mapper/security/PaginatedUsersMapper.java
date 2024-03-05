package de.lcag.jbox.backend.api.mapper.security;

import de.lcag.jbox.backend.api.resource.PaginatedUsersResource;
import de.lcag.jbox.backend.domain.model.query.Pagination;
import de.lcag.jbox.backend.domain.model.security.User;
import org.mapstruct.Mapper;

@Mapper(uses = {UserMapper.class})
public interface PaginatedUsersMapper {
  PaginatedUsersResource toResource(Pagination<User> userPagination);
}

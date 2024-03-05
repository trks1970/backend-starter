package de.lcag.jbox.backend.api.mapper.security;

import de.lcag.jbox.backend.api.resource.UserResource;
import de.lcag.jbox.backend.domain.model.security.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
  @Mapping(target = "roles", ignore = true)
  User toDomain(UserResource user);

  @BeanMapping(ignoreUnmappedSourceProperties = {"roles"})
  UserResource toResource(User user);
}

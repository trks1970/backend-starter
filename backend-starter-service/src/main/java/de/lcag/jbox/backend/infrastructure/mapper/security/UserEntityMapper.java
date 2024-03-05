package de.lcag.jbox.backend.infrastructure.mapper.security;

import de.lcag.jbox.backend.domain.model.security.User;
import de.lcag.jbox.backend.infrastructure.entity.security.UserEntity;
import de.lcag.jbox.backend.infrastructure.mapper.query.PageMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper extends PageMapper.PageContentMapper<UserEntity, User> {
  @BeanMapping(ignoreUnmappedSourceProperties = {"roles"})
  User toDomain(UserEntity entity);
}

package de.lcag.jbox.backend.infrastructure.mapper.security;

import de.lcag.jbox.backend.domain.model.security.User;
import de.lcag.jbox.backend.infrastructure.entity.security.UserEntity;
import de.lcag.jbox.backend.infrastructure.entity.security.UserRoleEntity;
import de.lcag.jbox.backend.infrastructure.mapper.query.PageMapper;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class UserEntityMapper implements PageMapper.PageContentMapper<UserEntity, User> {
  public abstract User toDomain(UserEntity entity);

  public abstract UserEntity toEntity(User user);

  public abstract UserEntity toEntity(User user, @MappingTarget UserEntity userEntity);

  protected Set<UUID> mapRoleUuids(Set<UserRoleEntity> value) {
    return value.stream()
        .map(userRoleEntity -> userRoleEntity.getId().getRoleId())
        .collect(Collectors.toSet());
  }
}

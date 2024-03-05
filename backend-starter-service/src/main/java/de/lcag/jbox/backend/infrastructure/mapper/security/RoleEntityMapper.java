package de.lcag.jbox.backend.infrastructure.mapper.security;

import de.lcag.jbox.backend.domain.model.security.Role;
import de.lcag.jbox.backend.infrastructure.entity.security.RoleEntity;
import de.lcag.jbox.backend.infrastructure.entity.security.RolePermissionEntity;
import de.lcag.jbox.backend.infrastructure.entity.security.UserRoleEntity;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class RoleEntityMapper {
  public abstract Role toDomain(RoleEntity entity);

  public abstract RoleEntity toEntity(Role role);

  public abstract RoleEntity toEntity(Role role, @MappingTarget RoleEntity roleEntity);

  protected Set<UUID> mapPermissionUuids(Set<RolePermissionEntity> value) {
    return value.stream()
        .map(rolePermissionEntity -> rolePermissionEntity.getId().getPermissionId())
        .collect(Collectors.toSet());
  }

  protected Set<UUID> mapUserUuids(Set<UserRoleEntity> value) {
    return value.stream()
        .map(userRoleEntity -> userRoleEntity.getId().getUserId())
        .collect(Collectors.toSet());
  }

  protected Set<UserRoleEntity> mapUsers(Set<UUID> value) {
    return new HashSet<>();
  }
}

package de.lcag.jbox.backend.infrastructure.mapper.security;

import de.lcag.jbox.backend.domain.model.security.Permission;
import de.lcag.jbox.backend.infrastructure.entity.security.PermissionEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PermissionEntityMapper {
  @BeanMapping(ignoreUnmappedSourceProperties = {"roles"})
  Permission toDomain(PermissionEntity entity);

  @Mapping(target = "roles", ignore = true)
  PermissionEntity toEntity(Permission Permission);

  @Mapping(target = "roles", ignore = true)
  PermissionEntity toEntity(
      Permission Permission, @MappingTarget PermissionEntity PermissionEntity);
}

package de.lcag.jbox.backend.infrastructure.entity.security;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Table(
    name = "system_role_permission",
    uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "permission_id"}))
@Audited
@Data
public class RolePermissionEntity {
  @EmbeddedId private RolePermissionId id;

  @ManyToOne(optional = false)
  @MapsId("roleId")
  RoleEntity role;

  @ManyToOne(optional = false)
  @MapsId("permissionId")
  PermissionEntity permission;

  @SuppressWarnings("unused")
  public RolePermissionEntity() {}

  public RolePermissionEntity(RoleEntity role, PermissionEntity permission) {
    this.role = role;
    this.permission = permission;
  }
}

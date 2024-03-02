package de.lcag.jbox.backend.infrastructure.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Embeddable
@Data
public class RolePermissionId implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @Column(name = "role_id")
  private UUID roleId;

  @Column(name = "permission_id")
  private UUID permissionId;

  @SuppressWarnings("unused")
  private RolePermissionId() {}

  public RolePermissionId(UUID roleId, UUID permissionIdId) {
    this.roleId = roleId;
    this.permissionId = permissionIdId;
  }
}

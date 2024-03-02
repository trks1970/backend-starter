package de.lcag.jbox.backend.infrastructure.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Embeddable
@Data
public class UserRoleId implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "role_id")
  private UUID roleId;

  @SuppressWarnings("unused")
  private UserRoleId() {}

  public UserRoleId(UUID userId, UUID roleId) {
    this.userId = userId;
    this.roleId = userId;
  }
}

package de.lcag.jbox.backend.infrastructure.entity.security;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(
    name = "system_user_role",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}))
@Audited
@Getter
@Setter
public class UserRoleEntity {
  @EmbeddedId private UserRoleId id;

  @ManyToOne(optional = false)
  @MapsId("userId")
  UserEntity user;

  @ManyToOne(optional = false)
  @MapsId("userId")
  RoleEntity role;

  @SuppressWarnings("unused")
  public UserRoleEntity() {}

  public UserRoleEntity(UserEntity user, RoleEntity role) {
    this.user = user;
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserRoleEntity that)) {
      return false;
    }
    return Objects.equals(user, that.user) && Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, role);
  }
}

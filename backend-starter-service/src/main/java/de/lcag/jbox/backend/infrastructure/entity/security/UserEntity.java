package de.lcag.jbox.backend.infrastructure.entity.security;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.lang.Nullable;

@Entity
@Table(
    name = "jbox_user",
    uniqueConstraints = @UniqueConstraint(name = "udx_user_name", columnNames = "name"))
@Audited
@Getter
@Setter
public class UserEntity {
  @Id //
  @GeneratedValue(strategy = GenerationType.UUID) //
  @Column(name = "id", nullable = false, updatable = false) //
  @Nullable
  UUID uuid = null;

  @Column(name = "name", unique = true, nullable = false)
  @Nullable
  String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
  Set<UserRoleEntity> roles = new HashSet<>();

  public void addRole(RoleEntity role) {
    UserRoleEntity userRole = new UserRoleEntity(this, role);
    roles.add(userRole);
    role.getUsers().add(userRole);
  }

  public void removeRole(RoleEntity role) {
    for (Iterator<UserRoleEntity> iterator = roles.iterator(); iterator.hasNext(); ) {
      UserRoleEntity userRole = iterator.next();

      if (userRole.getUser().equals(this) && userRole.getRole().equals(role)) {
        iterator.remove();
        userRole.getRole().getUsers().remove(userRole);
        userRole.setUser(null);
        userRole.setRole(null);
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserEntity that)) {
      return false;
    }
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}

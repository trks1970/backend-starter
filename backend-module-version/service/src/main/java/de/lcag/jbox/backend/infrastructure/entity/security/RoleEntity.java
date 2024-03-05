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
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.lang.Nullable;

@Entity
@Table(
    name = "jbox_role",
    uniqueConstraints = @UniqueConstraint(name = "udx_role_name", columnNames = "name"))
@Audited
@Getter
@Setter
public class RoleEntity {
  @Id //
  @GeneratedValue(strategy = GenerationType.UUID) //
  @Column(name = "id", nullable = false, updatable = false) //
  @Nullable
  UUID uuid = null;

  @Column(name = "name", unique = true, nullable = false)
  @Nullable
  String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "role")
  Set<UserRoleEntity> users = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "role")
  Set<RolePermissionEntity> permissions = new HashSet<>();

  public void addPermission(PermissionEntity permission) {
    RolePermissionEntity rolePermission = new RolePermissionEntity(this, permission);
    permissions.add(rolePermission);
    permission.getRoles().add(rolePermission);
  }

  public void removePermission(PermissionEntity permission) {
    for (Iterator<RolePermissionEntity> iterator = permissions.iterator(); iterator.hasNext(); ) {
      RolePermissionEntity rolePermission = iterator.next();

      if (rolePermission.getRole().equals(this)
          && rolePermission.getPermission().equals(permission)) {
        iterator.remove();
        rolePermission.getPermission().getRoles().remove(rolePermission);
        rolePermission.setRole(null);
        rolePermission.setPermission(null);
      }
    }
  }
}

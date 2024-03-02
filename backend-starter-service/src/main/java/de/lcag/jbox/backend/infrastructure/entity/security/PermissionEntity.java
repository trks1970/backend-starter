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
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.lang.Nullable;

@Entity
@Table(
    name = "jbox_permission",
    uniqueConstraints = @UniqueConstraint(name = "udx_permission_name", columnNames = "name"))
@Audited
@Getter
@Setter
public class PermissionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, updatable = false)
  @Nullable
  UUID uuid = null;

  @Column(name = "name", unique = true, nullable = false)
  @Nullable
  String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "permission")
  Set<RolePermissionEntity> roles = new HashSet<>();
}

package de.lcag.jbox.backend.domain.model.security;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.springframework.lang.Nullable;

public record Role(
    @Nullable UUID uuid, String name, String description, Set<Permission> permissions) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Role other)) {
      return false;
    }
    return Objects.equals(name, other.name) && Objects.equals(description, other.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }
}

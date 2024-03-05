package de.lcag.jbox.backend.domain.model.security;

import java.util.Objects;
import java.util.UUID;
import org.springframework.lang.Nullable;

public record User(@Nullable UUID uuid, String name) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User user)) {
      return false;
    }
    return Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}

package de.lcag.jbox.backend.infrastructure.mapper;

import de.lcag.jbox.backend.domain.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityReferenceMapper {
  @PersistenceContext private final EntityManager entityManager;

  public <T> T map(UUID uuid, @TargetType Class<T> type) {
    T entity = entityManager.find(type, uuid);
    if (entity == null) {
      throw new NotFoundException(type, "uuid", uuid.toString());
    }
    return entity;
  }
}

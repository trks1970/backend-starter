package de.lcag.jbox.backend.domain.repository;

import java.util.UUID;

public interface QueryRepository<D> {
  D findBy(UUID id);
}

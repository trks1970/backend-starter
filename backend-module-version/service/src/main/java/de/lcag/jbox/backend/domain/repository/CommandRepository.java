package de.lcag.jbox.backend.domain.repository;

import java.util.UUID;

public interface CommandRepository<D> {
  D create(D domain);
  D get(UUID id);
  D update(D domain);
  void delete(UUID id);
}

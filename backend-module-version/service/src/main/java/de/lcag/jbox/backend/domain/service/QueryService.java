package de.lcag.jbox.backend.domain.service;

import java.util.UUID;

public interface QueryService<D> {
  D findBy(UUID id);
}

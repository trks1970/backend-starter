package de.lcag.jbox.backend.domain.service;

import de.lcag.jbox.backend.domain.model.security.User;

public interface CommandService<D> {
  D create(D domain);
  D update(D domain);
}

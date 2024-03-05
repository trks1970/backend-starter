package de.lcag.jbox.backend.domain.service;

import de.lcag.jbox.backend.domain.repository.CommandRepository;

public abstract class AbstractCommandService<D> implements CommandService<D>{
  protected abstract CommandRepository<D> getCommandRepository();

  @Override
  public D create(D domain) {
    return getCommandRepository().create(domain);
  }

  @Override
  public D update(D domain) {
    return getCommandRepository().update(domain);
  }
}

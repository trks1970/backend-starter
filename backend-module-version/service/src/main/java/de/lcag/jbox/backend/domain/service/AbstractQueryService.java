package de.lcag.jbox.backend.domain.service;

import de.lcag.jbox.backend.domain.repository.QueryRepository;
import java.util.UUID;

public abstract class AbstractQueryService<D> implements QueryService<D>{

  protected abstract QueryRepository<D> getQueryRepository();

  @Override
  public D findBy(UUID id) {
    return getQueryRepository().findBy(id);
  }
}

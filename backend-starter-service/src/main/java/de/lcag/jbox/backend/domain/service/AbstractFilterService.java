package de.lcag.jbox.backend.domain.service;

import de.lcag.jbox.backend.domain.model.query.Pagination;
import de.lcag.jbox.backend.domain.model.query.PagingFilter;
import de.lcag.jbox.backend.domain.repository.FilterRepository;
import de.lcag.jbox.backend.domain.repository.QueryRepository;

public abstract class AbstractFilterService<D> extends AbstractQueryService<D> implements FilterService<D> {
  protected abstract FilterRepository<D> getFilterRepository();

  @Override
  protected final QueryRepository<D> getQueryRepository() {
    return getFilterRepository();
  }

  @Override
  public Pagination<D> filterBy(PagingFilter filter) {
    return getFilterRepository().filterBy(filter);
  }
}

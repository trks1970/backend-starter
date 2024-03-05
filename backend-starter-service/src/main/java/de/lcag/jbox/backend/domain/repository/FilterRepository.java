package de.lcag.jbox.backend.domain.repository;

import de.lcag.jbox.backend.domain.model.query.Pagination;
import de.lcag.jbox.backend.domain.model.query.PagingFilter;

public interface FilterRepository<D> extends QueryRepository<D>{
  Pagination<D> filterBy(PagingFilter filter);
}

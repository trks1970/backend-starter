package de.lcag.jbox.backend.domain.service;

import de.lcag.jbox.backend.domain.model.query.Pagination;
import de.lcag.jbox.backend.domain.model.query.PagingFilter;
import de.lcag.jbox.backend.domain.model.security.User;

public interface FilterService<D> extends QueryService<D> {
  Pagination<D> filterBy(PagingFilter filter);
}

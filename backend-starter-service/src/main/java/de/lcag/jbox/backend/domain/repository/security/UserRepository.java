package de.lcag.jbox.backend.domain.repository.security;

import de.lcag.jbox.backend.domain.model.query.Pagination;
import de.lcag.jbox.backend.domain.model.query.PagingFilter;
import de.lcag.jbox.backend.domain.model.security.User;

public interface UserRepository {

  Pagination<User> filterBy(PagingFilter filter);
}

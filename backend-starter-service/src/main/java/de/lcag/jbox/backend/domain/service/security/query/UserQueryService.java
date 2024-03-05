package de.lcag.jbox.backend.domain.service.security.query;

import de.lcag.jbox.backend.domain.model.query.Pagination;
import de.lcag.jbox.backend.domain.model.query.PagingFilter;
import de.lcag.jbox.backend.domain.model.security.User;
import de.lcag.jbox.backend.domain.repository.security.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserQueryService {
  private final UserRepository userRepository;

  public Pagination<User> filterBy(PagingFilter filter) {
    return userRepository.filterBy(filter);
  }
}

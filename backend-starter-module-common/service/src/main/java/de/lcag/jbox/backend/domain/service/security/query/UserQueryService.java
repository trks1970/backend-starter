package de.lcag.jbox.backend.domain.service.security.query;

import de.lcag.jbox.backend.domain.model.security.User;
import de.lcag.jbox.backend.domain.repository.FilterRepository;
import de.lcag.jbox.backend.domain.repository.security.UserRepository;
import de.lcag.jbox.backend.domain.service.AbstractFilterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserQueryService extends AbstractFilterService<User> {
  private final UserRepository userRepository;
  @Override
  protected FilterRepository<User> getFilterRepository() {
    return userRepository;
  }
}

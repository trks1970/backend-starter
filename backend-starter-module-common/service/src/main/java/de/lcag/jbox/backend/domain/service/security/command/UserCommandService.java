package de.lcag.jbox.backend.domain.service.security.command;

import de.lcag.jbox.backend.domain.model.security.User;
import de.lcag.jbox.backend.domain.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserCommandService {
  private final UserRepository userRepository;

  public User create(User user) {
    return userRepository.create(user);
  }

  public User update(User user) {
    return userRepository.update(user);
  }
}

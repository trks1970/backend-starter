package de.lcag.jbox.backend.domain.repository.security;

import de.lcag.jbox.backend.domain.model.security.User;
import de.lcag.jbox.backend.domain.repository.CommandRepository;
import de.lcag.jbox.backend.domain.repository.FilterRepository;

public interface UserRepository extends CommandRepository<User>, FilterRepository<User> {
}

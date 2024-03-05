package de.lcag.jbox.backend.infrastructure.mapper.security;

import de.lcag.jbox.backend.domain.model.query.Pagination;
import de.lcag.jbox.backend.domain.model.security.User;
import de.lcag.jbox.backend.infrastructure.entity.security.UserEntity;
import de.lcag.jbox.backend.infrastructure.mapper.query.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPageMapper extends PageMapper<UserEntity, User> {
  private final UserEntityMapper userEntityMapper;

  public Pagination<User> toDomain(Page<UserEntity> page) {
    return super.toDomain(page, userEntityMapper);
  }
}

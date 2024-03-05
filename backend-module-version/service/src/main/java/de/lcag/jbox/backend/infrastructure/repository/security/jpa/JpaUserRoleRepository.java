package de.lcag.jbox.backend.infrastructure.repository.security.jpa;

import de.lcag.jbox.backend.infrastructure.entity.security.UserRoleEntity;
import de.lcag.jbox.backend.infrastructure.entity.security.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {}

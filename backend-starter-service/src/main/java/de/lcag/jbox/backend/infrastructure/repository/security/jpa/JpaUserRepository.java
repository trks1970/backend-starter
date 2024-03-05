package de.lcag.jbox.backend.infrastructure.repository.security.jpa;

import de.lcag.jbox.backend.infrastructure.entity.security.UserEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;

public interface JpaUserRepository
    extends JpaRepository<UserEntity, UUID>,
        JpaSpecificationExecutor<UserEntity>,
        RevisionRepository<UserEntity, UUID, Long> {}

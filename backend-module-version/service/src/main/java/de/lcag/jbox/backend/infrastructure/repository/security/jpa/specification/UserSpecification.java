package de.lcag.jbox.backend.infrastructure.repository.security.jpa.specification;

import de.lcag.jbox.backend.domain.model.query.PagingFilter;
import de.lcag.jbox.backend.infrastructure.entity.security.UserEntity;
import de.lcag.jbox.backend.infrastructure.entity.security.UserEntity_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSpecification {
  public static Specification<UserEntity> filter(PagingFilter filter) {
    // TODO: generalize filter method
    return name(filter.filter().fieldFilters().getFirst().value());
  }

  public static Specification<UserEntity> name(String name) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get(UserEntity_.NAME), criteriaBuilder.literal(name));
  }
}

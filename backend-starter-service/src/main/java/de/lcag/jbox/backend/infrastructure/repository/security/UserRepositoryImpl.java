package de.lcag.jbox.backend.infrastructure.repository.security;

import static de.lcag.jbox.backend.infrastructure.repository.security.jpa.specification.UserSpecification.filter;

import de.lcag.jbox.backend.domain.model.query.Pagination;
import de.lcag.jbox.backend.domain.model.query.PagingFilter;
import de.lcag.jbox.backend.domain.model.query.SortBy.SortOrder;
import de.lcag.jbox.backend.domain.model.security.User;
import de.lcag.jbox.backend.domain.repository.security.UserRepository;
import de.lcag.jbox.backend.infrastructure.mapper.security.UserPageMapper;
import de.lcag.jbox.backend.infrastructure.repository.security.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private final UserPageMapper userPageMapper;
  private final JpaUserRepository jpaUserRepository;

  @Override
  public Pagination<User> filterBy(PagingFilter filter) {
    PageRequest pageRequest =
        PageRequest.of(
            filter.pageRequest().pageNumber(),
            filter.pageRequest().pageSize(),
            Sort.by(
                filter.sortRequest().sortBy().stream()
                    .map(
                        sortBy -> {
                          if (sortBy.order() == SortOrder.ASC) {
                            return Sort.Order.asc(sortBy.field());
                          } else {
                            return Sort.Order.desc(sortBy.field());
                          }
                        })
                    .toList()));
    return userPageMapper.toDomain(jpaUserRepository.findAll(filter(filter), pageRequest));
  }
}

package de.lcag.jbox.backend.api.controller.security;

import de.lcag.jbox.backend.api.UsersApi;
import de.lcag.jbox.backend.api.mapper.security.PaginatedUsersMapper;
import de.lcag.jbox.backend.api.mapper.security.PagingFilterMapper;
import de.lcag.jbox.backend.api.mapper.security.UserMapper;
import de.lcag.jbox.backend.api.resource.PaginatedRolesResource;
import de.lcag.jbox.backend.api.resource.PaginatedUsersResource;
import de.lcag.jbox.backend.api.resource.PagingFilterResource;
import de.lcag.jbox.backend.api.resource.UserResource;
import de.lcag.jbox.backend.domain.service.security.command.UserCommandService;
import de.lcag.jbox.backend.domain.service.security.query.UserQueryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.base-path:}")
@Slf4j
public class UsersController implements UsersApi {
  private final UserMapper userMapper;
  private final PagingFilterMapper pagingFilterMapper;
  private final PaginatedUsersMapper paginatedUsersMapper;
  private final UserQueryService userQueryService;
  private final UserCommandService userCommandService;

  @Override
  public ResponseEntity<UserResource> createUser(UserResource userResource) {
    return ResponseEntity.ok(
        userMapper.toResource(userCommandService.create(userMapper.toDomain(userResource))));
  }
  @Override
  public ResponseEntity<UserResource> getUser(UUID id) {
    return ResponseEntity.ok(
        userMapper.toResource(
        userQueryService.findBy(id)
        )
    );
  }

  @Override
  public ResponseEntity<UserResource> updateUser(UserResource userResource) {
    return ResponseEntity.ok(
        userMapper.toResource(userCommandService.update(userMapper.toDomain(userResource))));
  }

  @Override
  public ResponseEntity<Void> deleteUser(UUID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ResponseEntity<PaginatedUsersResource> filterUsers(
      PagingFilterResource pagingFilterResource) {
    return ResponseEntity.ok(
        paginatedUsersMapper.toResource(
            userQueryService.filterBy(pagingFilterMapper.toDomain(pagingFilterResource))));
  }

  @Override
  public ResponseEntity<List<PaginatedUsersResource>> getPermissionsOfUser(UUID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ResponseEntity<List<PaginatedRolesResource>> getRolesOfUser(UUID id) {
    throw new UnsupportedOperationException();
  }

}

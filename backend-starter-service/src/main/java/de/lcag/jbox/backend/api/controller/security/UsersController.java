package de.lcag.jbox.backend.api.controller.security;

import de.lcag.jbox.backend.api.UsersApi;
import de.lcag.jbox.backend.api.resource.UserResource;
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

  @Override
  public ResponseEntity<UserResource> getUser(UUID id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ResponseEntity<UserResource> getUserByName(String name) {
    throw new UnsupportedOperationException();
  }
}

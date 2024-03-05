package de.lcag.jbox.backend.api.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.lcag.jbox.backend.api.resource.ApiErrorResource;
import de.lcag.jbox.backend.domain.exception.DomainException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {
  GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler(new ObjectMapper());

  @Test
  void errorAttributes() {
    // given
    WebRequest webRequest = mock(WebRequest.class);
    // when
    Map<String, Object> attributes =
        globalExceptionHandler
            .errorAttributes()
            .getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
    // then
    assertThat(attributes.get("status")).isEqualTo(999);
  }

  @Test
  void handleDomainException() {
    // given
    DomainException exception = mock(DomainException.class);
    // when
    ResponseEntity<ApiErrorResource> response =
        globalExceptionHandler.handleDomainException(exception);
    // then
    assertThat(response.getStatusCode().value()).isEqualTo(500);
    String detail = Objects.requireNonNull(response.getBody()).getDetail().toString();
    assertThat(detail).startsWith(DomainException.class.getName());
  }

  @Test
  void handleInternalServerException() {
    // given
    IOException exception = mock(IOException.class);
    // when
    ResponseEntity<ApiErrorResource> response =
        globalExceptionHandler.handleInternalServerException(exception);
    // then
    assertThat(response.getStatusCode().value()).isEqualTo(500);
    String detail = Objects.requireNonNull(response.getBody()).getDetail().toString();
    assertThat(detail).startsWith(IOException.class.getName());
  }

  @Test
  void handleExceptionInternal() {
    // given
    IOException exception = mock(IOException.class);
    WebRequest request = mock(WebRequest.class);
    // when
    ResponseEntity<Object> response =
        globalExceptionHandler.handleExceptionInternal(
            exception, null, HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    // then
    ApiErrorResource error = (ApiErrorResource) Objects.requireNonNull(response).getBody();
    assert error != null;
    assertThat(error.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }
}

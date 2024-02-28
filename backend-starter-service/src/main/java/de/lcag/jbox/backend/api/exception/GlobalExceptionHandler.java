package de.lcag.jbox.backend.api.exception;

import de.lcag.jbox.backend.api.resource.ApiErrorResource;
import de.lcag.jbox.backend.domain.exception.DomainException;
import de.lcag.jbox.backend.domain.exception.NotFoundException;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper jacksonObjectMapper;

    /**
     * This bean overrides the content and schema of Spring Boot's default error responses.
     */
    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> defaultErrorResponse = super.getErrorAttributes(webRequest, options);
                ApiErrorResource error = new ApiErrorResource()
                    .status(defaultErrorResponse.get("status") != null ? (Integer) defaultErrorResponse.get("status") : 0)
                    .message(defaultErrorResponse.get("message") != null ? defaultErrorResponse.get("message").toString() : "");
                return jacksonObjectMapper.convertValue(error, new TypeReference<>() {
                });
            }
        };
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiErrorResource> handleNotFoundException(NotFoundException exception) {
        log.warn("", exception);
        return new ResponseEntity<>(
            new ApiErrorResource()
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage()),
            HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<ApiErrorResource> handleDomainException(DomainException exception) {
        log.warn("", exception);
        return new ResponseEntity<>(
            new ApiErrorResource()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorResource> handleInternalServerException(Exception exception) {
        log.error("", exception);
        return new ResponseEntity<>(
            new ApiErrorResource()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage() != null ? exception.getMessage() : ""),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * @see [controlleradvice-not-triggered-for-jackson-errors](https://stackoverflow.com/questions/49242100/controlleradvice-not-triggered-for-jackson-errors)
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception exception,
        @Nullable Object body,
        HttpHeaders headers,
        HttpStatusCode statusCode,
        WebRequest request) {
        return new ResponseEntity<>(
            new ApiErrorResource()
                .status(statusCode.value())
                .message(exception.getMessage() != null ? exception.getMessage() : ""),
            statusCode
        );
    }
}

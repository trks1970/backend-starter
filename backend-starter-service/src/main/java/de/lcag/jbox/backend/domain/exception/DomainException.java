package de.lcag.jbox.backend.domain.exception;

import java.io.Serial;

public abstract class DomainException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1L;

  protected DomainException(String message) {
    super(message);
  }

  protected DomainException(String message, Throwable cause) {
    super(message, cause);
  }

  protected DomainException(Throwable cause) {
    super(cause);
  }
}

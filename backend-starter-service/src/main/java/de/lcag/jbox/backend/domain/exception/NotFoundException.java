package de.lcag.jbox.backend.domain.exception;

import java.io.Serial;
import java.util.List;

public class NotFoundException extends DomainException {
  @Serial private static final long serialVersionUID = 1L;

  public NotFoundException(Class<?> type, List<String> lookup, List<String> values) {
    super(type.getSimpleName() + " not found by " + lookup + " values " + values);
  }
}

package de.lcag.jbox.backend.domain.exception;

import java.util.List;

public class NotFoundException extends DomainException {
  public NotFoundException(Class<?> type, List<String> lookup, List<String> values) {
    super(type.getSimpleName() + " not found by " + lookup + " values " + values);
  }
}

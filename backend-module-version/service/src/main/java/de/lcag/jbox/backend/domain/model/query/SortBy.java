package de.lcag.jbox.backend.domain.model.query;

public record SortBy(String field, SortOrder order) {
  public enum SortOrder {
    ASC,
    DESC
  }
}

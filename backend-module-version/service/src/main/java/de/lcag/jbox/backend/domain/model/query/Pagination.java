package de.lcag.jbox.backend.domain.model.query;

import java.util.List;

public record Pagination<T>(
    Integer totalPages,
    Long totalElements,
    Integer pageSize,
    Integer pageNumber,
    Boolean first,
    Boolean last,
    Integer numberOfElements,
    Boolean empty,
    List<T> content) {}

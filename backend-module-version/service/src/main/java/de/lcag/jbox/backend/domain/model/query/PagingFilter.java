package de.lcag.jbox.backend.domain.model.query;

public record PagingFilter(Filter filter, PageRequest pageRequest, SortRequest sortRequest) {}

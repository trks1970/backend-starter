package de.lcag.jbox.backend.domain.model.query;

import java.util.LinkedHashSet;

public record SortRequest(LinkedHashSet<SortBy> sortBy) {}

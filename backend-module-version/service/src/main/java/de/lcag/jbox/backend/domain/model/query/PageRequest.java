package de.lcag.jbox.backend.domain.model.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PageRequest(
    @Min(value = 1) //
        Integer pageNumber,
    @Min(value = 1) //
        @Max(value = 250) //
        Integer pageSize) {}

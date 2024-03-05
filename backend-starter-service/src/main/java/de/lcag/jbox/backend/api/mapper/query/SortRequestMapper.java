package de.lcag.jbox.backend.api.mapper.query;

import de.lcag.jbox.backend.api.resource.SortRequestResource;
import de.lcag.jbox.backend.domain.model.query.SortBy;
import de.lcag.jbox.backend.domain.model.query.SortRequest;
import java.util.LinkedHashSet;
import org.springframework.stereotype.Component;

@Component
public class SortRequestMapper {
  public SortRequest toDomain(SortRequestResource sortRequestResource) {
    return new SortRequest(
        new LinkedHashSet<>(
            sortRequestResource.getSortBy().stream()
                .map(
                    sortByResource ->
                        new SortBy(
                            sortByResource.getSortField(),
                            SortBy.SortOrder.valueOf(sortByResource.getSortOrder().name())))
                .toList()));
  }
}

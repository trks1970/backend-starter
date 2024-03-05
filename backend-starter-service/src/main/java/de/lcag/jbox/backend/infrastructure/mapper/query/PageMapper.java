package de.lcag.jbox.backend.infrastructure.mapper.query;

import de.lcag.jbox.backend.domain.model.query.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper<E, D> {
  protected Pagination<D> toDomain(Page<E> page, PageContentMapper<E, D> pageContentMapper) {
    return new Pagination<>(
        page.getTotalPages(),
        page.getTotalElements(),
        page.getSize(),
        page.getNumber(),
        page.isFirst(),
        page.isLast(),
        page.getNumberOfElements(),
        page.isEmpty(),
        page.getContent().stream().map(pageContentMapper::toDomain).toList());
  }

  public interface PageContentMapper<E, D> {
    D toDomain(E entity);
  }
}

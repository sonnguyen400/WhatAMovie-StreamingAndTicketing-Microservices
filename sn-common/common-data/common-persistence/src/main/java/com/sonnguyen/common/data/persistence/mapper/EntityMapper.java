package com.sonnguyen.common.data.persistence.mapper;

import java.util.Collection;
import java.util.List;

public interface EntityMapper<D, E> {
    D toDomain(E entity);

    E toEntity(D domain);

    List<D> toDomain(Collection<E> entityList);

    List<E> toEntity(Collection<D> domainList);
}

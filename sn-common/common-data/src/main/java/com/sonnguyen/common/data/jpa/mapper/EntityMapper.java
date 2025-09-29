package com.sonnguyen.common.data.jpa.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    D toDomain(E entity);

    E toEntity(D domain);

    List<D> toDomain(List<E> entityList);

    List<E> toEntity(List<D> domainList);
}

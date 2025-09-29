package com.sonnguyen.common.data.jpa.domain.repository;

import java.util.Collection;
import java.util.Optional;

public interface DomainRepository<D, ID> {
    D save(D domain);

    Collection<D> saveAll(Collection<D> domains);

    Optional<D> findById(ID id);

    D getById(ID id);

    boolean existsById(ID id);

    long count();

    Collection<D> findAll();

    Collection<D> findAllById(Collection<ID> ids);

    Collection<D> enrichAll(Collection<D> domains);

    D enrich(D domain);
}

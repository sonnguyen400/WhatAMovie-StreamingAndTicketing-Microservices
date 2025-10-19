package com.sonnguyen.common.data.core;

import java.util.Collection;
import java.util.Optional;

public interface DomainRepository<DOM, ID> {
    DOM save(DOM domain);

    Collection<DOM> saveAll(Collection<DOM> domains);

    Optional<DOM> findById(ID id);

    DOM getById(ID id);

    boolean existsById(ID id);

    long count();

    Collection<DOM> findAll();

    Collection<DOM> findAllById(Collection<ID> ids);

    Collection<DOM> enrichAll(Collection<DOM> domains);

    DOM enrich(DOM domain);
}

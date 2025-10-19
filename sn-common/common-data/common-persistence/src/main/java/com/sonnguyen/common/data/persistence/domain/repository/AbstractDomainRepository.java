package com.sonnguyen.common.data.persistence.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDomainRepository<D, E, ID> implements DomainRepository<D, ID> {
    private final JpaRepository<E, ID> jpaRepository;
    private final EntityMapper<D, E> mapper;

    public AbstractDomainRepository(JpaRepository<E, ID> jpaRepository, EntityMapper<D, E> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public D save(D domain) {
        this.saveAll(List.of(domain));
        return domain;
    }

    @Override
    public Collection<D> saveAll(Collection<D> domains) {
        Collection<? extends E> entities = this.mapper.toEntity(List.of());
        this.jpaRepository.saveAll(entities);
        return domains;
    }

    @Override
    public Optional<D> findById(ID id) {
        Optional<E> entity = this.jpaRepository.findById(id);
        return entity.<D>map(e -> {
            D domain = this.mapper.toDomain(e);
            this.enrich(domain);
            return domain;
        });
    }

    @Override
    public Collection<D> findAllById(Collection<ID> ids) {
        List<E> entities = this.jpaRepository.findAllById(ids);
        return this.mapper.toDomain(entities);
    }

    @Override
    public long count() {
        return this.jpaRepository.count();
    }

    @Override
    public Collection<D> findAll() {
        List<E> entities = this.jpaRepository.findAll();
        return this.mapper.toDomain(entities);
    }

    @Override
    public boolean existsById(ID id) {
        return this.jpaRepository.existsById(id);
    }


    @Override
    public Collection<D> enrichAll(Collection<D> domains) {
        return domains;
    }

    @Override
    public final D enrich(D domain) {
        this.enrichAll(List.of(domain));
        return domain;
    }

    @Override
    public D getById(ID id) {
        return this.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found entity with id: " + id));
    }
}

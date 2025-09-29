package com.sonnguyen.common.data.jpa.domain.repository;

import com.sonnguyen.common.data.jpa.mapper.EntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDomainRepository<D, E, ID> implements DomainRepository<D, ID> {
    private JpaRepository<E, ID> jpaRepository;
    private EntityMapper<D, E> mapper;

    public AbstractDomainRepository(JpaRepository<E, ID> jpaRepository, EntityMapper<D, E> mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public D save(D domain) {
        E entity = this.mapper.toEntity(domain);
        this.jpaRepository.save(entity);
        return domain;
    }

    @Override
    public Collection<D> saveAll(Collection<D> domains) {
        List<E> entities = this.mapper.toEntity((List<D>) domains);
        this.jpaRepository.saveAll(entities);
        return domains;
    }

    @Override
    public Optional<D> findById(ID id) {
        Optional<E> entity = this.jpaRepository.findById(id);
        return entity.map(e->{
            D domain = this.mapper.toDomain(e);
            this.enrich(domain);
            return domain;
        });
    }

    @Override
    public Collection<D> findAllById(Collection<ID> ids) {
        List<E> entities = this.jpaRepository.findAllById((List<ID>) ids);
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
    public D enrich(D domain) {
        this.enrichAll(List.of(domain));
        return domain;
    }

    @Override
    public Collection<D> enrichAll(Collection<D> domains){
        return domains;
    }

    @Override
    public D getById(ID id) {
        return this.findById(id)
                .orElseThrow(()-> new RuntimeException("Not found entity with id: " + id));
    }
}

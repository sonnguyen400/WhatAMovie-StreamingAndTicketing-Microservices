package com.sonnguyen.common.data.mongo.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.common.data.mongo.mapper.DocumentMapper;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDomainRepository<DOM, DOC, ID> implements DomainRepository<DOM, ID> {
    private final MongoRepository<DOC, ID> mongoRepository;
    private final DocumentMapper<DOM, DOC> mapper;

    public AbstractDomainRepository(MongoRepository<DOC, ID> mongoRepository, DocumentMapper<DOM, DOC> mapper) {
        this.mongoRepository = mongoRepository;
        this.mapper = mapper;
    }

    @Override
    public DOM save(DOM domain) {
        this.saveAll(List.of(domain));
        return domain;
    }

    @Override
    public Collection<DOM> saveAll(Collection<DOM> domains) {
        Collection<? extends DOC> documents = this.mapper.toDocument(domains);
        this.mongoRepository.saveAll(documents);
        return domains;
    }

    @Override
    public Optional<DOM> findById(ID id) {
        return this.mongoRepository.findById(id)
                .<DOM>map(this.mapper::toDomain)
                .map(this::enrich);
    }

    @Override
    public DOM getById(ID id) {
        return this.findById(id).orElseThrow(() ->
                new RuntimeException("Not found document with id: " + id)
        );
    }

    @Override
    public boolean existsById(ID id) {
        return this.mongoRepository.existsById(id);
    }

    @Override
    public long count() {
        return this.mongoRepository.count();
    }

    @Override
    public Collection<DOM> findAll() {
        return this.mapper.toDomain(this.mongoRepository.findAll());
    }

    @Override
    public Collection<DOM> findAllById(Collection<ID> ids) {
        return this.mapper.toDomain(this.mongoRepository.findAllById(ids));
    }

    @Override
    public Collection<DOM> enrichAll(Collection<DOM> domains) {
        return domains;
    }

    @Override
    public final DOM enrich(DOM domain) {
        this.enrichAll(List.of(domain));
        return domain;
    }
}

package com.sonnguyen.sncatalogue.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sncatalogue.domain.Tag;
import com.sonnguyen.sncatalogue.domain.repository.TagRepository;
import com.sonnguyen.sncatalogue.infrastructure.mapper.TagEntityMapper;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.TagEntity;
import com.sonnguyen.sncatalogue.infrastructure.persistence.repository.TagEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TagRepositoryRepositoryImpl extends AbstractDomainRepository<Tag, TagEntity, UUID>
        implements TagRepository {
    private final TagEntityRepository tagEntityRepository;
    private final TagEntityMapper tagEntityMapper;

    public TagRepositoryRepositoryImpl(TagEntityRepository tagEntityRepository, TagEntityMapper tagEntityMapper) {
        super(tagEntityRepository, tagEntityMapper);
        this.tagEntityRepository = tagEntityRepository;
        this.tagEntityMapper = tagEntityMapper;
    }
}

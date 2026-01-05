package com.sonnguyen.snultility.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.snultility.domain.Tag;
import com.sonnguyen.snultility.domain.repository.TagRepository;
import com.sonnguyen.snultility.infrastructure.mapper.TagEntityMapper;
import com.sonnguyen.snultility.infrastructure.persistence.entity.TagEntity;
import com.sonnguyen.snultility.infrastructure.persistence.repository.TagEntityRepository;
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

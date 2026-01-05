package com.sonnguyen.snultility.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.snultility.domain.ContentTag;
import com.sonnguyen.snultility.domain.repository.ContentTagRepository;
import com.sonnguyen.snultility.infrastructure.mapper.ContentTagEntityMapper;
import com.sonnguyen.snultility.infrastructure.persistence.entity.ContentTagEntity;
import com.sonnguyen.snultility.infrastructure.persistence.repository.ContentTagEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class ContentTagRepositoryImpl extends AbstractDomainRepository<ContentTag, ContentTagEntity, UUID>
        implements ContentTagRepository {
    private final ContentTagEntityRepository contentTagEntityRepository;
    private final ContentTagEntityMapper contentTagEntityMapper;

    public ContentTagRepositoryImpl(ContentTagEntityRepository contentTagEntityRepository,
                                    ContentTagEntityMapper contentTagEntityMapper) {
        super(contentTagEntityRepository, contentTagEntityMapper);
        this.contentTagEntityRepository = contentTagEntityRepository;
        this.contentTagEntityMapper = contentTagEntityMapper;
    }

    @Override
    public Collection<ContentTag> findByDomainIdAndDomainType(UUID domainId, DomainType domainType) {
        return this.contentTagEntityMapper.toDomain(this.contentTagEntityRepository.findAllByDomainIdAndType(domainId, domainType));
    }
}

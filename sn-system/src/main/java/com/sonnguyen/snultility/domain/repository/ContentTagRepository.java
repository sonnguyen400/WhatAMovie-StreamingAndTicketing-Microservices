package com.sonnguyen.snultility.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.snultility.domain.ContentTag;

import java.util.Collection;
import java.util.UUID;

public interface ContentTagRepository extends DomainRepository<ContentTag, UUID> {
    Collection<ContentTag> findByDomainIdAndDomainType(UUID domainId, DomainType domainType);
}

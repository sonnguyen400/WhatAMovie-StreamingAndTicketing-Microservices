package com.sonnguyen.snultility.infrastructure.persistence.repository;

import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.snultility.infrastructure.persistence.entity.ContentTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface ContentTagEntityRepository extends JpaRepository<ContentTagEntity, UUID> {
    @Query("FROM ContentTagEntity c WHERE c.id = :domainId AND c.domainType = :domainType AND c.deleted = false")
    Collection<ContentTagEntity> findAllByDomainIdAndType(UUID domainId, DomainType domainType);
}

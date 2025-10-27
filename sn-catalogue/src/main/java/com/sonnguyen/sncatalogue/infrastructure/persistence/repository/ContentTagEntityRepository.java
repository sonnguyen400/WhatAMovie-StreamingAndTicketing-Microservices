package com.sonnguyen.sncatalogue.infrastructure.persistence.repository;

import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.ContentTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContentTagEntityRepository extends JpaRepository<ContentTagEntity, UUID> {
}

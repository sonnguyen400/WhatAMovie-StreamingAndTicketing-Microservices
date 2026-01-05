package com.sonnguyen.snultility.infrastructure.persistence.repository;

import com.sonnguyen.snultility.infrastructure.persistence.entity.TagEntity;
import com.sonnguyen.snultility.infrastructure.persistence.repository.custom.CustomTagEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagEntityRepository extends JpaRepository<TagEntity, UUID> , CustomTagEntityRepository {
}

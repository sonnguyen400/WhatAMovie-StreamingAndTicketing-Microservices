package com.sonnguyen.sniam.infrastructure.persistence.repository;

import com.sonnguyen.sniam.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, UUID> {

    @Query("FROM RoleEntity R WHERE R.deleted = false AND R.id in :ids")
    List<RoleEntity> findByIds(List<UUID> ids);
}

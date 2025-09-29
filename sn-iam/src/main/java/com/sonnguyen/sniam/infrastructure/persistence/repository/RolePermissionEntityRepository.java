package com.sonnguyen.sniam.infrastructure.persistence.repository;

import com.sonnguyen.sniam.infrastructure.persistence.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RolePermissionEntityRepository extends JpaRepository<RolePermissionEntity, UUID> {
    @Query("FROM RolePermissionEntity R WHERE R.deleted = false AND R.roleId in :roleIds")
    List<RolePermissionEntity> findByRoleIds(List<UUID> roleIds);
}

package com.sonnguyen.sniam.infrastructure.persistence.repository;

import com.sonnguyen.sniam.infrastructure.persistence.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleEntityRepository extends JpaRepository<UserRoleEntity, UUID> {

    @Query("FROM UserRoleEntity U WHERE U.deleted = false and U.userId in :userIds")
    List<UserRoleEntity> findByUserIds(List<UUID> userIds);
}

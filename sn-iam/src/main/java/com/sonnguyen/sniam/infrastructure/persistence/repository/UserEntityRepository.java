package com.sonnguyen.sniam.infrastructure.persistence.repository;

import com.sonnguyen.sniam.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    @Query("FROM UserEntity u WHERE u.deleted = false "
            + " and (u.username = :username"
            + " or u.email = :username"
            + " or u.phoneNumber = :username)")
    Optional<UserEntity> findByUsername(String username);

    @Query("FROM UserEntity u WHERE u.deleted = false"
            + " and u.username = :username"
            + " and u.email = :email"
            + " and u.phoneNumber = :phoneNumber")
    Optional<UserEntity> findDuplicated(String username, String email, String phoneNumber);
}

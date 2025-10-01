package com.sonnguyen.sniam.infrastructure.persistence.repository;

import com.sonnguyen.sniam.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {
    @Query("FROM ClientEntity C WHERE C.clientId = :id AND C.deleted = false")
    Optional<ClientEntity> findByClientId(String id);
}

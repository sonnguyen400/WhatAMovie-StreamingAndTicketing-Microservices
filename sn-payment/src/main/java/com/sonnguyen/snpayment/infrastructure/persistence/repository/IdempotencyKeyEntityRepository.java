package com.sonnguyen.snpayment.infrastructure.persistence.repository;

import com.sonnguyen.snpayment.infrastructure.persistence.entity.IdempotencyKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IdempotencyKeyEntityRepository extends JpaRepository<IdempotencyKeyEntity, UUID> {
}

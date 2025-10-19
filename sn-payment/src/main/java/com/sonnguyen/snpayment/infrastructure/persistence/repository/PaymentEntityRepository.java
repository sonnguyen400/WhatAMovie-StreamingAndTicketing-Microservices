package com.sonnguyen.snpayment.infrastructure.persistence.repository;

import com.sonnguyen.snpayment.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, UUID> {
    @Query("FROM PaymentEntity P JOIN IdempotencyKeyEntity K ON P.idempotencyKeyId = K.id WHERE K.key = :idempotencyKey AND P.deleted = FALSE AND K.deleted = false")
    Optional<PaymentEntity> findByIdempotencyKey(String idempotencyKey);
}

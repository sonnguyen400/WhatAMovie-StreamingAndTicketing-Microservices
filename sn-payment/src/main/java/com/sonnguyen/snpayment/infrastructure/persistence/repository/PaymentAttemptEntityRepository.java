package com.sonnguyen.snpayment.infrastructure.persistence.repository;

import com.sonnguyen.snpayment.infrastructure.persistence.entity.PaymentAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentAttemptEntityRepository extends JpaRepository<PaymentAttemptEntity, UUID> {
    @Query("FROM PaymentAttemptEntity pa WHERE pa.paymentId IN :paymentIds")
    Collection<PaymentAttemptEntity> findByPaymentIds(List<UUID> paymentIds);
}

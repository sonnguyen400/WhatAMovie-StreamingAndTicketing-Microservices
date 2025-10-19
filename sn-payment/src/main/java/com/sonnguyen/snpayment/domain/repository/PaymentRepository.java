package com.sonnguyen.snpayment.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.snpayment.domain.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends DomainRepository<Payment, UUID> {
    Optional<Payment> findByIdempotencyKey(String idempotencyKey);
}

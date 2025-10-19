package com.sonnguyen.snpayment.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.snpayment.domain.IdempotencyKey;

import java.util.UUID;

public interface IdempotencyKeyRepository extends DomainRepository<IdempotencyKey, UUID> {
}

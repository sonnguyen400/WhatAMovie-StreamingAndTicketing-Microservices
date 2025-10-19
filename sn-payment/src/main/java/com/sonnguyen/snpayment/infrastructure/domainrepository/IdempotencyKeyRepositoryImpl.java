package com.sonnguyen.snpayment.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.snpayment.domain.IdempotencyKey;
import com.sonnguyen.snpayment.domain.repository.IdempotencyKeyRepository;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.IdempotencyKeyEntity;
import com.sonnguyen.snpayment.infrastructure.persistence.mapper.IdempotencyKeyEntityMapper;
import com.sonnguyen.snpayment.infrastructure.persistence.repository.IdempotencyKeyEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class IdempotencyKeyRepositoryImpl extends AbstractDomainRepository<IdempotencyKey, IdempotencyKeyEntity, UUID>
        implements IdempotencyKeyRepository {

    private IdempotencyKeyEntityRepository idempotencyKeyEntityRepository;
    private IdempotencyKeyEntityMapper idempotencyKeyEntityMapper;

    public IdempotencyKeyRepositoryImpl(IdempotencyKeyEntityRepository idempotencyKeyEntityRepository, IdempotencyKeyEntityMapper idempotencyKeyEntityMapper) {
        super(idempotencyKeyEntityRepository, idempotencyKeyEntityMapper);
        this.idempotencyKeyEntityRepository = idempotencyKeyEntityRepository;
        this.idempotencyKeyEntityMapper = idempotencyKeyEntityMapper;
    }
}

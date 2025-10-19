package com.sonnguyen.snpayment.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snpayment.domain.IdempotencyKey;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.IdempotencyKeyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IdempotencyKeyEntityMapper extends EntityMapper<IdempotencyKey, IdempotencyKeyEntity> {
}

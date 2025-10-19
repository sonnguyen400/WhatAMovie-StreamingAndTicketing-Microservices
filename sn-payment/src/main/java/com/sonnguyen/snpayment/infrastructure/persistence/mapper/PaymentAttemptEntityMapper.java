package com.sonnguyen.snpayment.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snpayment.domain.PaymentAttempt;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.PaymentAttemptEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentAttemptEntityMapper extends EntityMapper<PaymentAttempt, PaymentAttemptEntity> {
}

package com.sonnguyen.snpayment.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snpayment.domain.PaymentDetail;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.PaymentDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentDetailEntityMapper extends EntityMapper<PaymentDetail, PaymentDetailEntity> {
}

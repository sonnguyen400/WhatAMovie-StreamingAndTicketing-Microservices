package com.sonnguyen.snpayment.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snpayment.domain.MerchantAccount;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.MerchantAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantAccountEntityMapper extends EntityMapper<MerchantAccount, MerchantAccountEntity> {
}

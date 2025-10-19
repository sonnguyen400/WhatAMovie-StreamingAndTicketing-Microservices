package com.sonnguyen.snpayment.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snpayment.domain.MerchantAccount;
import com.sonnguyen.snpayment.infrastructure.persistence.entity.MerchantAccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantAccountEntityMapper extends EntityMapper<MerchantAccount, MerchantAccountEntity> {
}

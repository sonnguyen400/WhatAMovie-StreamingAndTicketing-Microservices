package com.sonnguyen.sniam.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.Customer;
import com.sonnguyen.sniam.infrastructure.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerEntityMapper extends EntityMapper<Customer, CustomerEntity> {

}

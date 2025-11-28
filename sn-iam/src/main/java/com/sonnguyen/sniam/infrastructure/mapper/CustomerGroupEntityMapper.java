package com.sonnguyen.sniam.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.CustomerGroup;
import com.sonnguyen.sniam.infrastructure.persistence.entity.CustomerGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerGroupEntityMapper extends EntityMapper<CustomerGroup, CustomerGroupEntity> {
}

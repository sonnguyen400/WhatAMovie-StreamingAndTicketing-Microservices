package com.sonnguyen.sniam.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.GroupCustomerCompound;
import com.sonnguyen.sniam.infrastructure.persistence.entity.GroupCustomerCompoundEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupCustomerCompoundEntityMapper extends EntityMapper<GroupCustomerCompound, GroupCustomerCompoundEntity> {
}

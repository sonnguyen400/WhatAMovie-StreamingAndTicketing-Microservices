package com.sonnguyen.snsystem.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snsystem.domain.ApiLog;
import com.sonnguyen.snsystem.infrastructure.persistence.entity.ApiLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiLogEntityMapper extends EntityMapper<ApiLog, ApiLogEntity> {
}

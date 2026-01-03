package com.sonnguyen.snbuilding.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snbuilding.domain.Screening;
import com.sonnguyen.snbuilding.infrastructure.persistence.entity.ScreeningEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ScreeningEntityMapper extends EntityMapper<Screening, ScreeningEntity> {
}

package com.sonnguyen.snbuilding.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snbuilding.domain.Theater;
import com.sonnguyen.snbuilding.infrastructure.persistence.entity.TheaterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TheaterEntityMapper extends EntityMapper<Theater, TheaterEntity> {

}

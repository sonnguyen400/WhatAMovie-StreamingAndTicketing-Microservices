package com.sonnguyen.snbuilding.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snbuilding.domain.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SeatEntityMapper extends EntityMapper<Seat, com.sonnguyen.snbuilding.infrastructure.persistence.entity.SeatEntity> {
}

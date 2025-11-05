package com.sonnguyen.snbuilding.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snbuilding.domain.Room;
import com.sonnguyen.snbuilding.infrastructure.persistence.entity.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomEntityMapper extends EntityMapper<Room, RoomEntity> {
}

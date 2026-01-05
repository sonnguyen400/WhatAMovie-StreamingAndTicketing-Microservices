package com.sonnguyen.snultility.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snultility.domain.Tag;
import com.sonnguyen.snultility.infrastructure.persistence.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TagEntityMapper extends EntityMapper<Tag, TagEntity> {
}

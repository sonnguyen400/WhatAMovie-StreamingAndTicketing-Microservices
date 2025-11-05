package com.sonnguyen.sncatalogue.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sncatalogue.domain.Tag;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TagEntityMapper extends EntityMapper<Tag, TagEntity> {
}

package com.sonnguyen.sncatalogue.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sncatalogue.domain.ContentTag;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.ContentTagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContentTagEntityMapper extends EntityMapper<ContentTag, ContentTagEntity> {
}

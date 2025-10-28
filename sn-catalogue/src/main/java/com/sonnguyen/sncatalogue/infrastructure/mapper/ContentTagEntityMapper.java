package com.sonnguyen.sncatalogue.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sncatalogue.domain.ContentTag;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.ContentTagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContentTagEntityMapper extends EntityMapper<ContentTag, ContentTagEntity> {
}

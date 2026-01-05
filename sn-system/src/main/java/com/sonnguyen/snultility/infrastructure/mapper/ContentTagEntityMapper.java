package com.sonnguyen.snultility.infrastructure.mapper;


import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snultility.domain.ContentTag;
import com.sonnguyen.snultility.infrastructure.persistence.entity.ContentTagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContentTagEntityMapper extends EntityMapper<ContentTag, ContentTagEntity> {
}

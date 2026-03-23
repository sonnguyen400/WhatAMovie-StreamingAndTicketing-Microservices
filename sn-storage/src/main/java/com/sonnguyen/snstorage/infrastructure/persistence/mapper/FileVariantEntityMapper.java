package com.sonnguyen.snstorage.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snstorage.domain.FileVariant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileVariantEntityMapper extends EntityMapper<FileVariant, com.sonnguyen.snstorage.infrastructure.persistence.entity.FileVariantEntity> {
}

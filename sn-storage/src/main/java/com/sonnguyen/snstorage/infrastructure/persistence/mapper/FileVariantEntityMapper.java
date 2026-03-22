package com.sonnguyen.snstorage.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snstorage.domain.FileVariant;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileVariantEntityMapper extends EntityMapper<FileVariant, com.sonnguyen.snstorage.infrastructure.persistence.entity.FileVariantEntity> {
}

package com.sonnguyen.snstorage.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snstorage.domain.File;
import com.sonnguyen.snstorage.infrastructure.persistence.entity.FileEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileEntityMapper extends EntityMapper<File, FileEntity> {
}
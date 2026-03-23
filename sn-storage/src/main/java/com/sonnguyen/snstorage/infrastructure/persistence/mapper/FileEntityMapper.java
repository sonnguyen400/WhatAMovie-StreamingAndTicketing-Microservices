package com.sonnguyen.snstorage.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snstorage.domain.File;
import com.sonnguyen.snstorage.infrastructure.persistence.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileEntityMapper extends EntityMapper<File, FileEntity> {
}
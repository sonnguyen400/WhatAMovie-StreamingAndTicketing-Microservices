package com.sonnguyen.snstorage.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snstorage.domain.FileVersion;
import com.sonnguyen.snstorage.infrastructure.persistence.entity.FileVersionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileVersionEntityMapper extends EntityMapper<FileVersion, FileVersionEntity> {
}

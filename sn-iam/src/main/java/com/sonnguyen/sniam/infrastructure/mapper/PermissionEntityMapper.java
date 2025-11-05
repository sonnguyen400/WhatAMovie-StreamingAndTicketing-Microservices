package com.sonnguyen.sniam.infrastructure.mapper;


import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.infrastructure.persistence.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionEntityMapper extends EntityMapper<Permission, PermissionEntity> {
}

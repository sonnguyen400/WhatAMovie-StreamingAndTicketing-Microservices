package com.sonnguyen.sniam.infrastructure.mapper;


import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.RolePermission;
import com.sonnguyen.sniam.infrastructure.persistence.entity.RolePermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RolePermissionEntityMapper extends EntityMapper<RolePermission, RolePermissionEntity> {
}

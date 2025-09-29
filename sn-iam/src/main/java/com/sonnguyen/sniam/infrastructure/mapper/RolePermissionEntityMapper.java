package com.sonnguyen.sniam.infrastructure.mapper;


import com.sonnguyen.common.data.jpa.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.RolePermission;
import com.sonnguyen.sniam.infrastructure.persistence.entity.RolePermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePermissionEntityMapper extends EntityMapper<RolePermission, RolePermissionEntity> {
}

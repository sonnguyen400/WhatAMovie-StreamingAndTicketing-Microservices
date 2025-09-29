package com.sonnguyen.sniam.infrastructure.mapper;


import com.sonnguyen.common.data.jpa.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.Permission;
import com.sonnguyen.sniam.infrastructure.persistence.entity.PermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionEntityMapper extends EntityMapper<Permission, PermissionEntity> {
}

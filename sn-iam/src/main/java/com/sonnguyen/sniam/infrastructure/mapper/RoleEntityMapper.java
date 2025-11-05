package com.sonnguyen.sniam.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.Role;
import com.sonnguyen.sniam.infrastructure.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleEntityMapper extends EntityMapper<Role, RoleEntity> {
}

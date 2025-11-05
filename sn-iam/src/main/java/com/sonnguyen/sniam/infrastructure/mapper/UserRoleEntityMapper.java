package com.sonnguyen.sniam.infrastructure.mapper;


import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.UserRole;
import com.sonnguyen.sniam.infrastructure.persistence.entity.UserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleEntityMapper extends EntityMapper<UserRole, UserRoleEntity> {
}

package com.sonnguyen.sniam.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sniam.domain.Client;
import com.sonnguyen.sniam.infrastructure.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientEntityMapper extends EntityMapper<Client, ClientEntity> {
}

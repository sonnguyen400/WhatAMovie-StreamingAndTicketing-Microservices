package com.sonnguyen.snaddress.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snaddress.domain.AdministrativeRegion;
import com.sonnguyen.snaddress.infrastructure.persistence.entity.AdministrativeRegionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdministrativeRegionEntityMapper extends EntityMapper<AdministrativeRegion, AdministrativeRegionEntity> {
}

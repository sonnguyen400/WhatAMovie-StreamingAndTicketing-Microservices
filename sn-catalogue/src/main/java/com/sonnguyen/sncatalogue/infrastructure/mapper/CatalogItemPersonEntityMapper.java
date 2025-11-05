package com.sonnguyen.sncatalogue.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sncatalogue.domain.CatalogItemPerson;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.CatalogItemPersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CatalogItemPersonEntityMapper extends EntityMapper<CatalogItemPerson, CatalogItemPersonEntity> {
}

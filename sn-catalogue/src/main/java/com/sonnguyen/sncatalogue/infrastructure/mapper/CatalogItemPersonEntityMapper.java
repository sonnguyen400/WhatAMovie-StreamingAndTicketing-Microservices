package com.sonnguyen.sncatalogue.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sncatalogue.domain.CatalogItemPerson;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.CatalogItemPersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogItemPersonEntityMapper extends EntityMapper<CatalogItemPerson, CatalogItemPersonEntity> {
}

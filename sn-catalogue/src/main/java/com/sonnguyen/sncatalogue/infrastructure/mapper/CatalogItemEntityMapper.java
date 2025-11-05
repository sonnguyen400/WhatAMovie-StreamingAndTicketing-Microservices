package com.sonnguyen.sncatalogue.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.CatalogItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CatalogItemEntityMapper extends EntityMapper<CatalogItem, CatalogItemEntity> {
}

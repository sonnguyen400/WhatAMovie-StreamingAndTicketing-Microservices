package com.sonnguyen.sncatalogue.infrastructure.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.CatalogItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogItemEntityMapper extends EntityMapper<CatalogItem, CatalogItemEntity> {
}

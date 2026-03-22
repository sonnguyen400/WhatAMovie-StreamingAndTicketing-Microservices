package com.sonnguyen.sncatalogue.infrastructure.persistence.repository.custom;

import java.util.Collection;

import com.sonnguyen.sncatalogue.domain.query.CatalogItemSearchQuery;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.CatalogItemEntity;

public interface CustomCatalogEntityRepository {

	Collection<CatalogItemEntity> search(CatalogItemSearchQuery query);

	Long count(CatalogItemSearchQuery query);
}

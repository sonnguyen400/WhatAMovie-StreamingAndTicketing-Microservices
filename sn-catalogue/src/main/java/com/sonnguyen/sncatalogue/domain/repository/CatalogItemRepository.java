package com.sonnguyen.sncatalogue.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.domain.query.CatalogItemSearchQuery;

import java.util.Collection;
import java.util.UUID;

public interface CatalogItemRepository extends DomainRepository<CatalogItem, UUID> {

	Collection<CatalogItem> search(CatalogItemSearchQuery query);

	Long count(CatalogItemSearchQuery query);
}

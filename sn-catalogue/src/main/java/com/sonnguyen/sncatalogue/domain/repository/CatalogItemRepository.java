package com.sonnguyen.sncatalogue.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.sncatalogue.domain.CatalogItem;

import java.util.UUID;

public interface CatalogItemRepository extends DomainRepository<CatalogItem, UUID> {
}

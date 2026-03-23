package com.sonnguyen.sncatalogue.application.service;

import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemCreateUpdateRequest;
import com.sonnguyen.sncatalogue.domain.CatalogItem;

import java.util.UUID;

public interface CatalogItemCommandService {
    CatalogItem createCatalogItem(CatalogItemCreateUpdateRequest request);

    CatalogItem updateCatalogItem(UUID id, CatalogItemCreateUpdateRequest request);

    void deleteCatalogItemById(UUID id);
}

package com.sonnguyen.sncatalogue.application.service;

import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemCreateUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.response.CatalogItemDetailResponse;

public interface CatalogItemCommandService {
    CatalogItemDetailResponse createCatalogItem(CatalogItemCreateUpdateRequest request);
}

package com.sonnguyen.sncatalogue.application.service;

import java.util.Collection;
import java.util.UUID;

import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemSearchRequest;
import com.sonnguyen.sncatalogue.domain.CatalogItem;

public interface CatalogItemQueryService {

	PagingResponse<CatalogItem> search(CatalogItemSearchRequest request);

	CatalogItem getById(UUID id);

	Collection<CatalogItem> findByIds(FindByIdsRequest request);
}

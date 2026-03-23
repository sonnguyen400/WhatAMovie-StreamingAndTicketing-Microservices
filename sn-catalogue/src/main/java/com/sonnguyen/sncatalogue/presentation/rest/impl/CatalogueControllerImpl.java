package com.sonnguyen.sncatalogue.presentation.rest.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemCreateUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemSearchRequest;
import com.sonnguyen.sncatalogue.application.service.CatalogItemCommandService;
import com.sonnguyen.sncatalogue.application.service.CatalogItemQueryService;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.presentation.rest.CatalogueController;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CatalogueControllerImpl implements CatalogueController {
    CatalogItemCommandService catalogItemCommandService;
    CatalogItemQueryService catalogItemQueryService;

    @Override
    public Response<CatalogItem> getCatalogItemById(UUID id) {
        return Response.of(catalogItemQueryService.getById(id));
    }

    @Override
    public Response<CatalogItem> createCatalogItem(CatalogItemCreateUpdateRequest request) {
        return Response.of(catalogItemCommandService.createCatalogItem(request));
    }

    @Override
    public Response<CatalogItem> updateCatalogItemById(UUID id, CatalogItemCreateUpdateRequest request) {
        return Response.of(catalogItemCommandService.updateCatalogItem(id, request));
    }

    @Override
    public Response<Void> deleteCatalogItemById(@PathVariable UUID id) {
        catalogItemCommandService.deleteCatalogItemById(id);
        return Response.ok();
    }

    @Override
    public PagingResponse<CatalogItem> searchCatalogItems(CatalogItemSearchRequest request) {
        return catalogItemQueryService.search(request);
    }
}

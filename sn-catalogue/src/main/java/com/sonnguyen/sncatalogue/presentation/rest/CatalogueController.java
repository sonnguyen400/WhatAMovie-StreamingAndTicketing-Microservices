package com.sonnguyen.sncatalogue.presentation.rest;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemCreateUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemSearchRequest;
import com.sonnguyen.sncatalogue.application.dto.response.CatalogItemDetailResponse;
import com.sonnguyen.sncatalogue.domain.CatalogItem;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api")
public interface CatalogueController {
    @GetMapping("/v1/catalog-items/{id}")
    Response<CatalogItem> getCatalogItemById(@PathVariable UUID id);

    @PostMapping("/v1/catalog-items")
    Response<CatalogItem> createCatalogItem(@RequestBody CatalogItemCreateUpdateRequest request);

    @PutMapping("/v1/catalog-items/{id}")
    Response<CatalogItem> updateCatalogItemById(@PathVariable UUID id, @RequestBody CatalogItemCreateUpdateRequest request);

    @DeleteMapping("/v1/catalog-items/{id}")
    Response<Void> deleteCatalogItemById(@PathVariable UUID id);

    @PostMapping("/v1/catalog-items/search")
    PagingResponse<CatalogItem> searchCatalogItems(@RequestBody CatalogItemSearchRequest request);
}

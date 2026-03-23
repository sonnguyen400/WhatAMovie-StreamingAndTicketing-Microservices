package com.sonnguyen.sncatalogue.application.service.impl;

import com.sonnguyen.common.model.application.request.FindByIdsRequest;
import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.sncatalogue.application.dto.mapper.CatalogueCommandMapper;
import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemSearchRequest;
import com.sonnguyen.sncatalogue.application.service.CatalogItemQueryService;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.domain.query.CatalogItemSearchQuery;
import com.sonnguyen.sncatalogue.domain.repository.CatalogItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CatalogItemQueryServiceImpl implements CatalogItemQueryService {

    CatalogueCommandMapper catalogueCommandMapper;
    CatalogItemRepository catalogItemRepository;

    @Override
    public PagingResponse<CatalogItem> search(CatalogItemSearchRequest request) {
        CatalogItemSearchQuery query = this.catalogueCommandMapper.from(request);

        Long countLong = this.catalogItemRepository.count(query);

        if (Objects.equals(countLong, 0L)) {
            return PagingResponse.of(List.of(), countLong, query.getPageSize(), query.getPageIndex());
        }

        Collection<CatalogItem> catalogItems = this.catalogItemRepository.search(query);

        return PagingResponse.of(catalogItems, countLong, query.getPageSize(), query.getPageSize());
    }

    @Override
    public CatalogItem getById(UUID id) {
        return this.catalogItemRepository.getById(id);
    }

    @Override
    public Collection<CatalogItem> findByIds(FindByIdsRequest request) {
        return this.catalogItemRepository.findAllById(request.getIds());
    }
}

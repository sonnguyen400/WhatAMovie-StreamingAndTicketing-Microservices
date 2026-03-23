package com.sonnguyen.sncatalogue.application.service.impl;

import com.sonnguyen.sncatalogue.application.dto.mapper.CatalogueCommandMapper;
import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemCreateUpdateRequest;
import com.sonnguyen.sncatalogue.application.service.CatalogItemCommandService;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.domain.command.CatalogItemCreateOrUpdateCmd;
import com.sonnguyen.sncatalogue.domain.repository.CatalogItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CatalogItemCommandServiceImpl implements CatalogItemCommandService {

    CatalogueCommandMapper catalogueCommandMapper;
    CatalogItemRepository catalogItemRepository;

    @Override
    public CatalogItem createCatalogItem(CatalogItemCreateUpdateRequest request) {
        CatalogItemCreateOrUpdateCmd cmd = this.catalogueCommandMapper.from(request);
        List<UUID> childCatalogItemIds = cmd.getChildIds();
        List<CatalogItem> catalogItems = this.catalogItemRepository
                .findAllById(childCatalogItemIds)
                .stream()
                .toList();
        CatalogItem catalogItem = new CatalogItem(cmd, catalogItems);
        this.catalogItemRepository.save(catalogItem);
        return catalogItem;
    }

    @Override
    public CatalogItem updateCatalogItem(UUID id, CatalogItemCreateUpdateRequest request) {
        CatalogItemCreateOrUpdateCmd cmd = this.catalogueCommandMapper.from(request);
        List<UUID> childCatalogItemIds = cmd.getChildIds();
        List<CatalogItem> catalogItems = this.catalogItemRepository
                .findAllById(childCatalogItemIds)
                .stream()
                .toList();
        CatalogItem catalogItem = this.catalogItemRepository.getById(id);
        catalogItem.update(cmd, catalogItems);
        this.catalogItemRepository.save(catalogItem);
        return catalogItem;
    }

    @Override
    public void deleteCatalogItemById(UUID id) {
        CatalogItem catalogItem = this.catalogItemRepository.getById(id);
        catalogItem.delete();
        this.catalogItemRepository.save(catalogItem);
    }
}

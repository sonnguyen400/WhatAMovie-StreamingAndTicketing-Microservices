package com.sonnguyen.sncatalogue.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.domain.repository.CatalogItemRepository;
import com.sonnguyen.sncatalogue.infrastructure.mapper.CatalogItemEntityMapper;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.CatalogItemEntity;
import com.sonnguyen.sncatalogue.infrastructure.persistence.repository.CatalogItemEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CatalogItemRepositoryImpl extends AbstractDomainRepository<CatalogItem, CatalogItemEntity, UUID>
        implements CatalogItemRepository {

    private final CatalogItemEntityRepository catalogItemEntityRepository;
    private final CatalogItemEntityMapper catalogItemEntityMapper;

    public CatalogItemRepositoryImpl(CatalogItemEntityRepository catalogItemEntityRepository, CatalogItemEntityMapper catalogItemEntityMapper) {
        super(catalogItemEntityRepository, catalogItemEntityMapper);
        this.catalogItemEntityRepository = catalogItemEntityRepository;
        this.catalogItemEntityMapper = catalogItemEntityMapper;
    }
}

package com.sonnguyen.sncatalogue.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.sncatalogue.domain.CatalogItem;
import com.sonnguyen.sncatalogue.domain.repository.CatalogItemRepository;
import com.sonnguyen.sncatalogue.infrastructure.mapper.CatalogItemEntityMapper;
import com.sonnguyen.common.data.persistence.mapper.MessageLocaleEntityMapper;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.CatalogItemEntity;
import com.sonnguyen.common.data.persistence.entity.MessageLocaleEntity;
import com.sonnguyen.sncatalogue.infrastructure.persistence.repository.CatalogItemEntityRepository;
import com.sonnguyen.common.data.persistence.repository.MessageLocaleEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CatalogItemRepositoryImpl extends AbstractDomainRepository<CatalogItem, CatalogItemEntity, UUID>
        implements CatalogItemRepository {

    private final CatalogItemEntityRepository catalogItemEntityRepository;
    private final CatalogItemEntityMapper catalogItemEntityMapper;
    private final MessageLocaleEntityRepository messageLocaleEntityRepository;
    private final MessageLocaleEntityMapper messageLocaleEntityMapper;

    public CatalogItemRepositoryImpl(CatalogItemEntityRepository catalogItemEntityRepository,
                                     CatalogItemEntityMapper catalogItemEntityMapper,
                                     MessageLocaleEntityRepository messageLocaleEntityRepository,
                                     MessageLocaleEntityMapper messageLocaleEntityMapper) {
        super(catalogItemEntityRepository, catalogItemEntityMapper);
        this.catalogItemEntityRepository = catalogItemEntityRepository;
        this.catalogItemEntityMapper = catalogItemEntityMapper;
        this.messageLocaleEntityRepository = messageLocaleEntityRepository;
        this.messageLocaleEntityMapper = messageLocaleEntityMapper;
    }

    @Override
    @Transactional
    public Collection<CatalogItem> saveAll(Collection<CatalogItem> domains) {
        List<MessageLocale> messageLocales = domains.stream()
                .filter(it -> Objects.nonNull(it.getMessageLocales()))
                .flatMap(it -> it.getMessageLocales().stream())
                .toList();
        ArrayList<CatalogItem> catalogItems = domains.stream()
                .filter(it -> Objects.nonNull(it.getCatalogItems()))
                .flatMap(it -> it.getCatalogItems().stream())
                .collect(Collectors.toCollection(ArrayList::new));
        catalogItems.addAll(domains);


        List<CatalogItemEntity> catalogItemEntities = this.catalogItemEntityMapper.toEntity(catalogItems);
        List<MessageLocaleEntity> messageLocaleEntities = this.messageLocaleEntityMapper.toEntity(messageLocales);
        this.catalogItemEntityRepository.saveAll(catalogItemEntities);
        this.messageLocaleEntityRepository.saveAll(messageLocaleEntities);
        return domains;
    }
}

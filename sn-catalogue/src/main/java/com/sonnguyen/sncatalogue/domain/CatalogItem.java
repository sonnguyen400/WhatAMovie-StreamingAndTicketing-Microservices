package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.domain.Deletable;
import com.sonnguyen.common.model.domain.InternationalizationDomain;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.common.whatamoviemodel.domain.ContentTag;
import com.sonnguyen.sncatalogue.domain.command.CatalogItemCreateOrUpdateCmd;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class CatalogItem extends AuditingDomain implements Deletable, InternationalizationDomain {
    private UUID id;
    private String title;
    private String description;
    private String slug;
    private ContentType contentType;
    private ContentStatus status;
    private CatalogMetadata metadata;
    private DistributionChannel distributionChannel;
    private UUID parentId;
    private Integer version;
    private Boolean deleted;
    //Enrich
    private List<CatalogItem> catalogItems;
    private List<CatalogItemPerson> persons;
    private List<ContentTag> tags;
    private List<MessageLocale> messageLocales;

    public CatalogItem(CatalogItemCreateOrUpdateCmd cmd, List<CatalogItem> newChildCatalogItems) {
        this.id = IdUtils.nextId();
        this.title = cmd.getTitle();
        this.slug = cmd.getSlug();
        this.contentType = cmd.getContentType();
        this.status = cmd.getStatus();
        this.updateTagId(cmd.getTagIds());
        this.updateLocale(this.id, DomainType.CATALOGUE_ITEM, cmd.getMessageLocales());
        this.updateCatalogItems(newChildCatalogItems);
    }

    public void update(CatalogItemCreateOrUpdateCmd cmd, List<CatalogItem> newChildCatalogItems) {
        this.title = cmd.getTitle();
        this.slug = cmd.getSlug();
        this.contentType = cmd.getContentType();
        this.status = cmd.getStatus();
        this.updateTagId(cmd.getTagIds());
        this.updateLocale(this.id, DomainType.CATALOGUE_ITEM, cmd.getMessageLocales());
        this.updateCatalogItems(newChildCatalogItems);
    }

    public void updateCatalogItems(List<CatalogItem> newChildCatalogItems) {
        if (Objects.isNull(this.catalogItems)) {
            this.catalogItems = new ArrayList<>();
        }
        Set<UUID> newChildCatalogItemIds = newChildCatalogItems.stream().map(CatalogItem::getId).collect(Collectors.toSet());
        Set<UUID> existedChildCatalogItems = this.catalogItems.stream().map(CatalogItem::getId).collect(Collectors.toSet());
        this.catalogItems.forEach(it -> {
            it.detached();
            if (newChildCatalogItemIds.contains(it.getId())) {
                it.attachToParent(this.id);
            }
        });
        newChildCatalogItems.forEach(it -> {
            if (!existedChildCatalogItems.contains(it.getId())) {
                it.attachToParent(this.id);
            }
        });
    }

    private void detached() {
        this.parentId = null;
    }

    private void attachToParent(UUID parentId) {
        this.parentId = parentId;
    }

    private void updateTagId(List<UUID> tagIds) {
        tagIds.forEach(it -> {
            ContentTag contentTag = new ContentTag(this.id, it, DomainType.CATALOGUE_ITEM);
            this.tags.add(contentTag);
        });
    }

    @Override
    public void localize(MessageLocale messageLocale) {
        this.title = messageLocale.getProperty("title", this.title);
        this.description = messageLocale.getProperty("description", this.description);
    }

    @Override
    public void enrichMessageLocales(List<MessageLocale> messageLocales) {
        this.messageLocales = messageLocales;
    }

    @Override
    public void delete() {
        this.deleted = true;
        this.tags = new ArrayList<>();
    }

    @Override
    public void unDelete() {
        this.deleted = false;
    }
}

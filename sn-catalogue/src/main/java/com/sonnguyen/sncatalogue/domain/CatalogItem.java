package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.Deletable;
import com.sonnguyen.common.model.domain.InternationalizationDomain;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.util.IdUtils;
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
public class CatalogItem extends InternationalizationDomain implements Deletable {
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
        if (Objects.isNull(this.tags)) {
            this.tags = new ArrayList<>();
        }
        Set<UUID> existedTagIds = this.tags.stream().map(ContentTag::getTagId).collect(Collectors.toSet());
        tags.forEach(it -> {
            it.delete();
            if (tagIds.contains(it.getTagId())) {
                it.unDelete();
            }
        });
        tagIds.forEach(it -> {
            if (!existedTagIds.contains(it)) {
                ContentTag contentTag = new ContentTag(this.id, it, DomainType.CATALOGUE_ITEM);
                this.tags.add(contentTag);
            }
        });
    }

    @Override
    public void buildMessageLocalesByCode(LocaleCode localeCode) {
        this.getLocaleMessageByCode(localeCode).ifPresent(it -> {
            this.title = it.getProperty("title", this.title);
            this.description = it.getProperty("description", this.description);
        });
    }

    public void enrichMessageLocales(List<MessageLocale> messageLocales) {
        this.messageLocales = messageLocales;
    }

    @Override
    public void delete() {
        this.deleted = true;
    }

    @Override
    public void unDelete() {
        this.deleted = false;
    }
}

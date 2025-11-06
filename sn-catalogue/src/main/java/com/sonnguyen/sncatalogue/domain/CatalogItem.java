package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Getter
public class CatalogItem extends AuditingDomain {
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

    //Enrich
    private List<CatalogItem> catalogItems;
    private List<MessageLocale> messageLocales;
    private List<CatalogItemPerson> directors;
    private List<CatalogItemPerson> actors;

    private CatalogItem() {
    }

    private void addLocale(LocaleCode locale) {

    }

    public void enrichLocale(LocaleCode localeCode) {
        Optional<MessageLocale> localeOtp = this.messageLocales
                .stream()
                .filter(it -> Objects.equals(it.getLocale(), localeCode))
                .findFirst();
        if (localeOtp.isEmpty()) return;
        MessageLocale locale = localeOtp.get();
        this.title = locale.getProperty("title", this.title);
        this.description = locale.getProperty("description", this.description);
    }
}

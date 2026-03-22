package com.sonnguyen.snultility.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.domain.Deletable;
import com.sonnguyen.common.model.domain.InternationalizationDomain;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snultility.domain.cmd.TagCreateOrUpdateCmd;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@SuperBuilder
@Getter
public class Tag extends AuditingDomain implements InternationalizationDomain, Deletable {
    private UUID id;
    private String title;
    private List<MessageLocale> messageLocales;
    private List<ContentTag> contents;
    private Boolean deleted;

    public Tag(TagCreateOrUpdateCmd cmd) {
        this.id = IdUtils.nextId();
        this.title = cmd.getTitle();
        this.deleted = Boolean.FALSE;
        this.updateLocale(this.id, DomainType.TAG, cmd.getMessageLocales());
    }

    public void update(TagCreateOrUpdateCmd cmd) {
        this.title = cmd.getTitle();
        this.updateLocale(this.id, DomainType.TAG, cmd.getMessageLocales());
    }

    public void enrichMessageLocale(List<MessageLocale> messageLocales) {
        this.messageLocales = messageLocales;
    }

    public void setLocale(LocaleCode locale) {
        this.messageLocales.stream()
                .filter(it -> Objects.equals(it.getLocale(), locale))
                .findFirst()
                .ifPresent(it -> {
                    this.title = it.getProperty("title", this.title);
                });
    }

    public void enrichContentTags(List<ContentTag> domain) {
        this.contents = domain;
    }

    @Override
    public void localize(MessageLocale messageLocale) {
        this.title = messageLocale.getProperty("title", this.title);
    }

    @Override
    public void enrichMessageLocales(List<MessageLocale> messageLocales) {
        this.messageLocales = messageLocales;
    }

    public void delete() {
        this.deleted = false;
        this.messageLocales.forEach(MessageLocale::delete);
        this.contents.forEach(ContentTag::delete);
    }
}

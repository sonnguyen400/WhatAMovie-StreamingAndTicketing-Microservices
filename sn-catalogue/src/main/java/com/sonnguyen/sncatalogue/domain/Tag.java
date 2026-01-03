package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.InternationalizationDomain;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.sncatalogue.domain.command.TagCommandCreateOrUpdateCmd;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@SuperBuilder
public class Tag extends InternationalizationDomain {
    private UUID id;
    private String title;
    private List<MessageLocale> messageLocales;

    public Tag(TagCommandCreateOrUpdateCmd cmd) {
        this.id = IdUtils.nextId();
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

    @Override
    public void buildMessageLocalesByCode(LocaleCode localeCode) {
        this.getLocaleMessageByCode(localeCode).ifPresent(it -> {
            this.title = it.getProperty("title", this.title);
        });
    }
}

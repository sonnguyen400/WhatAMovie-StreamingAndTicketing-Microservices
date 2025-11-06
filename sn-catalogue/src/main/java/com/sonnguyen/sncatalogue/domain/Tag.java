package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.sncatalogue.domain.command.MessageLocaleCreateOrUpdateCmd;
import com.sonnguyen.sncatalogue.domain.command.TagCommandCreateOrUpdateCmd;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@SuperBuilder
public class Tag extends AuditingDomain {
    private UUID id;
    private String title;
    private List<MessageLocale> messageLocales;

    public Tag(TagCommandCreateOrUpdateCmd cmd){
        this.id = IdUtils.nextId();
        this.title = cmd.getTitle();
        this.updateMessageLocale(cmd.getMessageLocales());
    }

    private void updateMessageLocale(Map<LocaleCode, String> localeMessages) {
        if(Objects.isNull(localeMessages) || localeMessages.isEmpty()) return;
        List<MessageLocaleCreateOrUpdateCmd> messageLocaleCmds = localeMessages.entrySet().stream()
                .map(it->MessageLocaleCreateOrUpdateCmd.builder()
                        .domainType(DomainType.CATALOGUE_TAG)
                        .domainId(this.id)
                        .locale(it.getKey())
                        .properties(Map.of("title", it.getValue()))
                        .build())
                .toList();
        this.messageLocales = messageLocaleCmds.stream().map(MessageLocale::new).toList();

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
}

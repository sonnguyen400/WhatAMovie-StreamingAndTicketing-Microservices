package com.sonnguyen.common.model.domain;

import com.sonnguyen.common.model.domain.command.MessageLocaleCmd;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.util.DataUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
public abstract class InternationalizationDomain extends AuditingDomain {
    protected List<MessageLocale> messageLocales;

    protected void updateLocale(UUID domainId, DomainType domainType, List<MessageLocaleCmd> messageLocaleCmds) {
        if (Objects.isNull(this.messageLocales)) {
            this.messageLocales = new ArrayList<>();
        }
        Set<LocaleCode> existedLocaleCodes = this.messageLocales.stream().map(MessageLocale::getLocale).collect(Collectors.toSet());
        Map<LocaleCode, MessageLocaleCmd> messageLocaleCmdMap = messageLocaleCmds.stream().collect(Collectors.toMap(MessageLocaleCmd::getLocaleCode, Function.identity(), DataUtils::applyFirst));
        this.messageLocales.forEach(it -> {
            it.delete();
            MessageLocaleCmd messageLocaleCmd = messageLocaleCmdMap.getOrDefault(it.getLocale(), null);
            if (Objects.nonNull(messageLocaleCmd)) {
                it.unDelete();
                it.updateProperties(messageLocaleCmd.getMessages());
            }
        });
        messageLocaleCmdMap.forEach((key, value) -> {
            if (!existedLocaleCodes.contains(key)) {
                value.setDomainId(domainId);
                value.setDomainType(domainType);
                this.messageLocales.add(new MessageLocale(value));
            }
        });
    }

    protected Optional<MessageLocale> getLocaleMessageByCode(LocaleCode localeCode) {
        return this.messageLocales.stream()
                .filter(it -> Objects.equals(it.getLocale(), localeCode) && Boolean.FALSE.equals(it.getDeleted()))
                .findFirst();
    }

    public abstract void buildMessageLocalesByCode(LocaleCode localeCode);
}

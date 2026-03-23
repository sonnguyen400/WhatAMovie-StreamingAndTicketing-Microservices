package com.sonnguyen.common.model.domain;

import com.sonnguyen.common.model.domain.command.MessageLocaleCmd;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.util.DataUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;


public interface InternationalizationDomain {

    default void updateLocale(UUID domainId, DomainType domainType, List<MessageLocaleCmd> messageLocaleCmds) {
        final List<MessageLocale> messageLocales = new ArrayList<>();
        if (Objects.nonNull(this.getMessageLocales())) {
            messageLocales.addAll(this.getMessageLocales());
        }
        Set<LocaleCode> existedLocaleCodes = messageLocales.stream().map(MessageLocale::getLocale).collect(Collectors.toSet());
        Map<LocaleCode, MessageLocaleCmd> messageLocaleCmdMap = messageLocaleCmds.stream().collect(Collectors.toMap(MessageLocaleCmd::getLocaleCode, Function.identity(), DataUtils::applyFirst));
        messageLocales.forEach(it -> {
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
                messageLocales.add(new MessageLocale(value));
            }
        });
        this.enrichMessageLocales(messageLocales);
    }

    void localize(MessageLocale messageLocale);

    UUID getId();

    List<MessageLocale> getMessageLocales();

    void enrichMessageLocales(List<MessageLocale> messageLocales);
}

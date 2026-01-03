package com.sonnguyen.common.model.domain.command;

import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public abstract class MessageLocaleCmd {
    private LocaleCode localeCode;
    private Map<String, String> messages;
    private UUID domainId;
    private DomainType domainType;
}

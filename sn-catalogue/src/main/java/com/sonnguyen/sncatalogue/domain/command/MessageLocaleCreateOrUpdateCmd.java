package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.common.model.infrastructure.constant.DomainType;
import com.sonnguyen.common.model.infrastructure.constant.LocaleCode;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public abstract class MessageLocaleCreateOrUpdateCmd {
    private LocaleCode locale;
    private UUID domainId;
    private DomainType domainType;
    private Map<String, String> properties;
}

package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.constant.DomainType;
import com.sonnguyen.common.model.infrastructure.constant.LocaleCode;
import com.sonnguyen.common.util.DataUtils;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;
import java.util.UUID;

@Getter
@SuperBuilder
public class MessageLocale extends AuditingDomain {
    private UUID id;
    private LocaleCode locale;
    private UUID domainId;
    private DomainType domainType;
    private Map<String, String> properties;

    public String getProperty(String propertyName, String defaultValue) {
        return DataUtils.getOrDefault(properties.get(propertyName), defaultValue);
    }
}

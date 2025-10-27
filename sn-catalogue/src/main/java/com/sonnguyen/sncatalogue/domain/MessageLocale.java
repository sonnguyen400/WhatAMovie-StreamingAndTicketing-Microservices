package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.constant.DomainType;
import com.sonnguyen.common.model.infrastructure.constant.LocaleCode;
import com.sonnguyen.common.util.DataUtils;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.sncatalogue.domain.command.MessageLocaleCreateOrUpdateCmd;
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

    public MessageLocale(MessageLocaleCreateOrUpdateCmd cmd) {
        this.id = IdUtils.nextId();
        this.locale = cmd.getLocale();
        this.domainId = cmd.getDomainId();
        this.properties = cmd.getProperties();
    }

    public String getProperty(String propertyName, String defaultValue) {
        return DataUtils.getOrDefault(properties.get(propertyName), defaultValue);
    }
}

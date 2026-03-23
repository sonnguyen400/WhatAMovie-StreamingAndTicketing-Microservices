package com.sonnguyen.common.model.domain;

import com.sonnguyen.common.model.domain.command.MessageLocaleCmd;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.util.DataUtils;
import com.sonnguyen.common.util.IdUtils;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;
import java.util.UUID;

@Getter
@SuperBuilder
public class MessageLocale extends AuditingDomain implements Deletable {
    private UUID id;
    private LocaleCode locale;
    private UUID domainId;
    private DomainType domainType;
    private Map<String, String> properties;
    private Boolean deleted;

    public MessageLocale(MessageLocaleCmd cmd) {
        this.id = IdUtils.nextId();
        this.locale = cmd.getLocaleCode();
        this.domainId = cmd.getDomainId();
        this.properties = cmd.getMessages();
    }

    public void updateProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public String getProperty(String propertyName, String defaultValue) {
        return DataUtils.getOrDefault(properties.get(propertyName), defaultValue);
    }

    @Override
    public void delete() {
        this.deleted = true;
    }

    @Override
    public void unDelete() {
        this.deleted = false;
    }
}

package com.sonnguyen.common.data.persistence.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.common.model.domain.InternationalizationDomain;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;

import java.util.Collection;
import java.util.UUID;

public interface MessageLocaleRepository extends DomainRepository<MessageLocale, UUID> {
    <T extends InternationalizationDomain, ID> void enrichLocaleMessages(LocaleCode localeCode, DomainType domainType, Collection<T> domain);
}

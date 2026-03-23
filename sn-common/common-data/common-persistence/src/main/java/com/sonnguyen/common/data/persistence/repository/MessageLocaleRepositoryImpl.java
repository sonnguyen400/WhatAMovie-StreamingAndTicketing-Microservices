package com.sonnguyen.common.data.persistence.repository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.common.data.persistence.domain.repository.MessageLocaleRepository;
import com.sonnguyen.common.data.persistence.entity.MessageLocaleEntity;
import com.sonnguyen.common.data.persistence.entityrepository.MessageLocaleEntityRepository;
import com.sonnguyen.common.data.persistence.mapper.MessageLocaleEntityMapper;
import com.sonnguyen.common.model.domain.InternationalizationDomain;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class MessageLocaleRepositoryImpl extends AbstractDomainRepository<MessageLocale, MessageLocaleEntity, UUID>
        implements MessageLocaleRepository {
    private final MessageLocaleEntityRepository messageLocaleEntityRepository;

    public MessageLocaleRepositoryImpl(MessageLocaleEntityRepository messageLocaleEntityRepository,
                                       MessageLocaleEntityMapper messageLocaleEntityMapper) {
        super(messageLocaleEntityRepository, messageLocaleEntityMapper);
        this.messageLocaleEntityRepository = messageLocaleEntityRepository;
    }

    @Override
    public <T extends InternationalizationDomain, ID> void enrichLocaleMessages(LocaleCode localeCode, DomainType domainType, Collection<T> domains) {
        List<UUID> ids = domains.stream().map(InternationalizationDomain::getId).toList();
        List<MessageLocaleEntity> messageLocaleEntities = this.messageLocaleEntityRepository
                .findByDomainTypeAndDomainIdAndLocaleCode(domainType, ids, localeCode);
        Map<UUID, List<MessageLocale>> messageLocales = this.mapper.toDomain(messageLocaleEntities)
                .stream()
                .collect(Collectors.groupingBy(MessageLocale::getDomainId));
        for (T domain : domains) {
            List<MessageLocale> locales = messageLocales.getOrDefault(domain.getId(), List.of());
            locales.forEach(domain::localize);
        }
    }
}

package com.sonnguyen.sncatalogue.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.common.data.persistence.entity.MessageLocaleEntity;
import com.sonnguyen.common.data.persistence.entityrepository.MessageLocaleEntityRepository;
import com.sonnguyen.common.data.persistence.mapper.MessageLocaleEntityMapper;
import com.sonnguyen.common.model.domain.MessageLocale;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.sncatalogue.domain.Person;
import com.sonnguyen.sncatalogue.domain.query.PersonSearchQuery;
import com.sonnguyen.sncatalogue.domain.repository.PersonRepository;
import com.sonnguyen.sncatalogue.infrastructure.mapper.PersonEntityMapper;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.PersonEntity;
import com.sonnguyen.sncatalogue.infrastructure.persistence.repository.PersonEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoryImpl extends AbstractDomainRepository<Person, PersonEntity, UUID>
        implements PersonRepository {
    private final PersonEntityRepository personEntityRepository;
    private final MessageLocaleEntityRepository messageLocaleEntityRepository;
    private final MessageLocaleEntityMapper messageLocaleEntityMapper;

    public PersonRepositoryImpl(PersonEntityRepository personEntityRepository,
                                PersonEntityMapper personEntityMapper,
                                MessageLocaleEntityRepository messageLocaleEntityRepository,
                                MessageLocaleEntityMapper messageLocaleEntityMapper) {
        super(personEntityRepository, personEntityMapper);
        this.personEntityRepository = personEntityRepository;
        this.messageLocaleEntityRepository = messageLocaleEntityRepository;
        this.messageLocaleEntityMapper = messageLocaleEntityMapper;
    }

    @Override
    public List<Person> search(PersonSearchQuery query) {
        return this.mapper.toDomain(this.personEntityRepository.search(query));
    }

    @Override
    public Long count(PersonSearchQuery query) {
        return this.personEntityRepository.count(query);
    }

    @Override
    public Collection<Person> enrichAll(Collection<Person> domains) {
        List<UUID> domainIds = domains.stream().map(Person::getId).toList();
        List<MessageLocaleEntity> messageLocaleEntities = this.messageLocaleEntityRepository.findAllByDomainIdAndDomainType(DomainType.CATALOGUE_PERSON, domainIds);
        Map<UUID, List<MessageLocale>> messageLocales = this.messageLocaleEntityMapper.toDomain(messageLocaleEntities).stream()
                .collect(Collectors.groupingBy(MessageLocale::getDomainId));
        domains.forEach(domain -> {
            List<MessageLocale> locales = messageLocales.get(domain.getId());
            if (Objects.nonNull(locales)) {
                domain.enrichMessageLocales(locales);
            }
        });
        return domains;
    }
}

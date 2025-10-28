package com.sonnguyen.sncatalogue.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.sncatalogue.domain.Person;
import com.sonnguyen.sncatalogue.domain.repository.PersonRepository;
import com.sonnguyen.sncatalogue.infrastructure.mapper.PersonEntityMapper;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.PersonEntity;
import com.sonnguyen.sncatalogue.infrastructure.persistence.repository.PersonEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PersonRepositoryImpl extends AbstractDomainRepository<Person, PersonEntity, UUID>
        implements PersonRepository {
    private final PersonEntityRepository personEntityRepository;
    private final PersonEntityMapper personEntityMapper;

    public PersonRepositoryImpl(PersonEntityRepository personEntityRepository, PersonEntityMapper personEntityMapper) {
        super(personEntityRepository, personEntityMapper);
        this.personEntityRepository = personEntityRepository;
        this.personEntityMapper = personEntityMapper;
    }
}

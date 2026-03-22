package com.sonnguyen.sncatalogue.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.sncatalogue.domain.Person;
import com.sonnguyen.sncatalogue.domain.query.PersonSearchQuery;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends DomainRepository<Person, UUID> {
    List<Person> search(PersonSearchQuery query);

    Long count(PersonSearchQuery query);
}

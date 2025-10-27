package com.sonnguyen.sncatalogue.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.sncatalogue.domain.Person;

import java.util.UUID;

public interface PersonRepository extends DomainRepository<Person, UUID> {
}

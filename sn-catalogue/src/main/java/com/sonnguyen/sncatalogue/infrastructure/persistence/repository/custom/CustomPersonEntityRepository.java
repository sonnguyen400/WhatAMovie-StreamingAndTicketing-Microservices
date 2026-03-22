package com.sonnguyen.sncatalogue.infrastructure.persistence.repository.custom;

import com.sonnguyen.sncatalogue.domain.query.PersonSearchQuery;
import com.sonnguyen.sncatalogue.infrastructure.persistence.entity.PersonEntity;

import java.util.List;

public interface CustomPersonEntityRepository {
    List<PersonEntity> search(PersonSearchQuery query);

    Long count(PersonSearchQuery query);
}

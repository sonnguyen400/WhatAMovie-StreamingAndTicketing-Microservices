package com.sonnguyen.snaddress.infrastructure.persistence.repository.custom;

import com.sonnguyen.snaddress.domain.query.CountrySearchQuery;
import com.sonnguyen.snaddress.infrastructure.persistence.entity.CountryEntity;

import java.util.List;

public interface CustomCountryEntityRepository {
    List<CountryEntity> search(CountrySearchQuery query);

    long count(CountrySearchQuery query);
}

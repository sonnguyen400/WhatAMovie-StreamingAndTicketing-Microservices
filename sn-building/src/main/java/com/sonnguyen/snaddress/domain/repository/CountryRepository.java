package com.sonnguyen.snaddress.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.snaddress.domain.Country;
import com.sonnguyen.snaddress.domain.query.CountrySearchQuery;

import java.util.List;
import java.util.UUID;

public interface CountryRepository extends DomainRepository<Country, UUID> {
    List<Country> search(CountrySearchQuery query);

    long count(CountrySearchQuery query);
}

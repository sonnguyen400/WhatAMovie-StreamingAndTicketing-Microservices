package com.sonnguyen.snaddress.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.snaddress.domain.Country;
import com.sonnguyen.snaddress.domain.query.CountrySearchQuery;
import com.sonnguyen.snaddress.domain.repository.CountryRepository;
import com.sonnguyen.snaddress.infrastructure.persistence.entity.CountryEntity;
import com.sonnguyen.snaddress.infrastructure.persistence.mapper.CountryEntityMapper;
import com.sonnguyen.snaddress.infrastructure.persistence.repository.CountryEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CountryRepositoryImpl extends AbstractDomainRepository<Country, CountryEntity, UUID>
        implements CountryRepository {

    private final CountryEntityRepository countryEntityRepository;

    public CountryRepositoryImpl(CountryEntityRepository countryEntityRepository,
                                 CountryEntityMapper countryEntityMapper) {
        super(countryEntityRepository, countryEntityMapper);
        this.countryEntityRepository = countryEntityRepository;
    }

    @Override
    public List<Country> search(CountrySearchQuery query) {
        return this.mapper.toDomain(this.countryEntityRepository.search(query));
    }

    @Override
    public long count(CountrySearchQuery query) {
        return this.countryEntityRepository.count(query);
    }
}

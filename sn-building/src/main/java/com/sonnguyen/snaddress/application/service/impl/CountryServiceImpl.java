package com.sonnguyen.snaddress.application.service.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snaddress.application.dto.request.CountryCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.CountrySearchRequest;
import com.sonnguyen.snaddress.application.dto.request.CountryUpdateRequest;
import com.sonnguyen.snaddress.application.mapper.AddressMapper;
import com.sonnguyen.snaddress.application.service.CountryService;
import com.sonnguyen.snaddress.domain.Country;
import com.sonnguyen.snaddress.domain.cmd.CountryCreateCmd;
import com.sonnguyen.snaddress.domain.cmd.CountryUpdateCmd;
import com.sonnguyen.snaddress.domain.query.CountrySearchQuery;
import com.sonnguyen.snaddress.domain.repository.CountryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class CountryServiceImpl implements CountryService {
    CountryRepository countryRepository;
    AddressMapper addressMapper;

    @Override
    public Country create(CountryCreateRequest request) {
        CountryCreateCmd cmd = addressMapper.from(request);
        Country country = new Country(cmd);
        countryRepository.save(country);
        return country;
    }

    @Override
    public Country update(UUID id, CountryUpdateRequest request) {
        Country country = countryRepository.getById(id);
        CountryUpdateCmd cmd = addressMapper.from(request);
        country.update(cmd);
        countryRepository.save(country);
        return country;
    }

    @Override
    public void delete(UUID id) {
        Country country = countryRepository.getById(id);
        country.delete();
        countryRepository.save(country);
    }

    @Override
    @Transactional(readOnly = true)
    public Country getById(UUID id) {
        return countryRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PagingResponse<Country> search(CountrySearchRequest request) {
        CountrySearchQuery query = addressMapper.from(request);
        long count = countryRepository.count(query);
        if (count == 0) {
            return PagingResponse.of(new ArrayList<>(), 0L, request.getPageSize(), request.getPageIndex());
        }
        List<Country> countries = countryRepository.search(query);
        return PagingResponse.of(countries, count, request.getPageSize(), request.getPageIndex());
    }
}

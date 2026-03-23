package com.sonnguyen.snaddress.application.service;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snaddress.application.dto.request.CountryCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.CountrySearchRequest;
import com.sonnguyen.snaddress.application.dto.request.CountryUpdateRequest;
import com.sonnguyen.snaddress.domain.Country;

import java.util.UUID;

public interface CountryService {
    Country create(CountryCreateRequest request);

    Country update(UUID id, CountryUpdateRequest request);

    void delete(UUID id);

    Country getById(UUID id);

    PagingResponse<Country> search(CountrySearchRequest request);
}

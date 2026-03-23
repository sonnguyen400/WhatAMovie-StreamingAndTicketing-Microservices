package com.sonnguyen.snaddress.presentation.rest.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snaddress.application.dto.request.CountryCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.CountrySearchRequest;
import com.sonnguyen.snaddress.application.dto.request.CountryUpdateRequest;
import com.sonnguyen.snaddress.application.service.CountryService;
import com.sonnguyen.snaddress.domain.Country;
import com.sonnguyen.snaddress.presentation.rest.CountryController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CountryControllerImpl implements CountryController {
    CountryService countryService;

    @Override
    public Response<Country> create(CountryCreateRequest request) {
        return Response.of(countryService.create(request));
    }

    @Override
    public Response<Country> getById(UUID id) {
        return Response.of(countryService.getById(id));
    }

    @Override
    public Response<Country> update(UUID id, CountryUpdateRequest request) {
        return Response.of(countryService.update(id, request));
    }

    @Override
    public Response<Void> delete(UUID id) {
        countryService.delete(id);
        return Response.ok();
    }

    @Override
    public PagingResponse<Country> search(CountrySearchRequest request) {
        return countryService.search(request);
    }
}

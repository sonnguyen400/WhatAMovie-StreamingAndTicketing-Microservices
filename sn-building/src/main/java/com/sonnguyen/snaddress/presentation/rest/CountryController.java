package com.sonnguyen.snaddress.presentation.rest;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snaddress.application.dto.request.CountryCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.CountrySearchRequest;
import com.sonnguyen.snaddress.application.dto.request.CountryUpdateRequest;
import com.sonnguyen.snaddress.domain.Country;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api")
public interface CountryController {

    @PostMapping("/v1/countries/create")
    Response<Country> create(@RequestBody @Valid CountryCreateRequest request);

    @PostMapping("/v1/countries/{id}")
    Response<Country> getById(@PathVariable UUID id);

    @PostMapping("/v1/countries/{id}/update")
    Response<Country> update(@PathVariable UUID id, @RequestBody @Valid CountryUpdateRequest request);

    @PostMapping("/v1/countries/{id}/delete")
    Response<Void> delete(@PathVariable UUID id);

    @PostMapping("/v1/countries/search")
    PagingResponse<Country> search(@RequestBody CountrySearchRequest request);
}

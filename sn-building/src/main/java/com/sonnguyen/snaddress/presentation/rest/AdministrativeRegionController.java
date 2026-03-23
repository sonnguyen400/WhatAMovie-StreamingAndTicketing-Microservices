package com.sonnguyen.snaddress.presentation.rest;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionSearchRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionUpdateRequest;
import com.sonnguyen.snaddress.domain.AdministrativeRegion;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
public interface AdministrativeRegionController {

    @PostMapping("/v1/administrative-regions/create")
    Response<AdministrativeRegion> create(@RequestBody @Valid AdministrativeRegionCreateRequest request);

    @PostMapping("/v1/administrative-regions/{id}")
    Response<AdministrativeRegion> getById(@PathVariable UUID id);

    @PostMapping("/v1/administrative-regions/{id}/update")
    Response<AdministrativeRegion> update(@PathVariable UUID id,
                                          @RequestBody @Valid AdministrativeRegionUpdateRequest request);

    @PostMapping("/v1/administrative-regions/{id}/delete")
    Response<Void> delete(@PathVariable UUID id);

    @PostMapping("/v1/administrative-regions/search")
    PagingResponse<AdministrativeRegion> search(@RequestBody AdministrativeRegionSearchRequest request);

    @PostMapping("/v1/administrative-regions/find-by-parent")
    Response<List<AdministrativeRegion>> findByParentId(@RequestBody UUID parentId);
}

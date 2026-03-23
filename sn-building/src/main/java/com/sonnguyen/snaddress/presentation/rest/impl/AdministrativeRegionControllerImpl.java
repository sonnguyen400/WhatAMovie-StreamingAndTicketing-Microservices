package com.sonnguyen.snaddress.presentation.rest.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionSearchRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionUpdateRequest;
import com.sonnguyen.snaddress.application.service.AdministrativeRegionService;
import com.sonnguyen.snaddress.domain.AdministrativeRegion;
import com.sonnguyen.snaddress.presentation.rest.AdministrativeRegionController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdministrativeRegionControllerImpl implements AdministrativeRegionController {
    AdministrativeRegionService regionService;

    @Override
    public Response<AdministrativeRegion> create(AdministrativeRegionCreateRequest request) {
        return Response.of(regionService.create(request));
    }

    @Override
    public Response<AdministrativeRegion> getById(UUID id) {
        return Response.of(regionService.getById(id));
    }

    @Override
    public Response<AdministrativeRegion> update(UUID id, AdministrativeRegionUpdateRequest request) {
        return Response.of(regionService.update(id, request));
    }

    @Override
    public Response<Void> delete(UUID id) {
        regionService.delete(id);
        return Response.ok();
    }

    @Override
    public PagingResponse<AdministrativeRegion> search(AdministrativeRegionSearchRequest request) {
        return regionService.search(request);
    }

    @Override
    public Response<List<AdministrativeRegion>> findByParentId(UUID parentId) {
        return Response.of(regionService.findByParentId(parentId));
    }
}

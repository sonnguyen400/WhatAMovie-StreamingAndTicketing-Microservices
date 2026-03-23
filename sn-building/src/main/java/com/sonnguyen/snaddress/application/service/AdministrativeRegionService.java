package com.sonnguyen.snaddress.application.service;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionSearchRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionUpdateRequest;
import com.sonnguyen.snaddress.domain.AdministrativeRegion;

import java.util.List;
import java.util.UUID;

public interface AdministrativeRegionService {
    AdministrativeRegion create(AdministrativeRegionCreateRequest request);

    AdministrativeRegion update(UUID id, AdministrativeRegionUpdateRequest request);

    void delete(UUID id);

    AdministrativeRegion getById(UUID id);

    PagingResponse<AdministrativeRegion> search(AdministrativeRegionSearchRequest request);

    List<AdministrativeRegion> findByParentId(UUID parentId);
}

package com.sonnguyen.snaddress.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.snaddress.domain.AdministrativeRegion;
import com.sonnguyen.snaddress.domain.query.AdministrativeRegionSearchQuery;

import java.util.List;
import java.util.UUID;

public interface AdministrativeRegionRepository extends DomainRepository<AdministrativeRegion, UUID> {
    List<AdministrativeRegion> search(AdministrativeRegionSearchQuery query);

    long count(AdministrativeRegionSearchQuery query);

    List<AdministrativeRegion> findByParentId(UUID parentId);

    List<AdministrativeRegion> findByCountryId(UUID countryId);
}
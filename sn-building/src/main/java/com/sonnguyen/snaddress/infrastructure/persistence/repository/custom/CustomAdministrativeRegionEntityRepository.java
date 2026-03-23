package com.sonnguyen.snaddress.infrastructure.persistence.repository.custom;

import com.sonnguyen.snaddress.domain.query.AdministrativeRegionSearchQuery;
import com.sonnguyen.snaddress.infrastructure.persistence.entity.AdministrativeRegionEntity;

import java.util.List;

public interface CustomAdministrativeRegionEntityRepository {
    List<AdministrativeRegionEntity> search(AdministrativeRegionSearchQuery query);

    long count(AdministrativeRegionSearchQuery query);
}

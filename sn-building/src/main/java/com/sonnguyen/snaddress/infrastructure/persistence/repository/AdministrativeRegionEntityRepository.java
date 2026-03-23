package com.sonnguyen.snaddress.infrastructure.persistence.repository;

import com.sonnguyen.snaddress.infrastructure.persistence.entity.AdministrativeRegionEntity;
import com.sonnguyen.snaddress.infrastructure.persistence.repository.custom.CustomAdministrativeRegionEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdministrativeRegionEntityRepository
        extends JpaRepository<AdministrativeRegionEntity, UUID>, CustomAdministrativeRegionEntityRepository {

    List<AdministrativeRegionEntity> findByParentIdAndDeletedFalse(UUID parentId);

    List<AdministrativeRegionEntity> findByCountryIdAndDeletedFalse(UUID countryId);
}

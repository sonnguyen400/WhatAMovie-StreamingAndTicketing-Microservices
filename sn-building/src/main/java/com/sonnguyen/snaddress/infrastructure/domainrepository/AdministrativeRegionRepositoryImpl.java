package com.sonnguyen.snaddress.infrastructure.domainrepository;

import com.sonnguyen.common.data.persistence.domain.repository.AbstractDomainRepository;
import com.sonnguyen.snaddress.domain.AdministrativeRegion;
import com.sonnguyen.snaddress.domain.query.AdministrativeRegionSearchQuery;
import com.sonnguyen.snaddress.domain.repository.AdministrativeRegionRepository;
import com.sonnguyen.snaddress.infrastructure.persistence.entity.AdministrativeRegionEntity;
import com.sonnguyen.snaddress.infrastructure.persistence.mapper.AdministrativeRegionEntityMapper;
import com.sonnguyen.snaddress.infrastructure.persistence.repository.AdministrativeRegionEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AdministrativeRegionRepositoryImpl
        extends AbstractDomainRepository<AdministrativeRegion, AdministrativeRegionEntity, UUID>
        implements AdministrativeRegionRepository {

    private final AdministrativeRegionEntityRepository regionEntityRepository;

    public AdministrativeRegionRepositoryImpl(AdministrativeRegionEntityRepository regionEntityRepository,
                                              AdministrativeRegionEntityMapper regionEntityMapper) {
        super(regionEntityRepository, regionEntityMapper);
        this.regionEntityRepository = regionEntityRepository;
    }

    @Override
    public List<AdministrativeRegion> search(AdministrativeRegionSearchQuery query) {
        return this.mapper.toDomain(this.regionEntityRepository.search(query));
    }

    @Override
    public long count(AdministrativeRegionSearchQuery query) {
        return this.regionEntityRepository.count(query);
    }

    @Override
    public List<AdministrativeRegion> findByParentId(UUID parentId) {
        return this.mapper.toDomain(this.regionEntityRepository.findByParentIdAndDeletedFalse(parentId));
    }

    @Override
    public List<AdministrativeRegion> findByCountryId(UUID countryId) {
        return this.mapper.toDomain(this.regionEntityRepository.findByCountryIdAndDeletedFalse(countryId));
    }
}

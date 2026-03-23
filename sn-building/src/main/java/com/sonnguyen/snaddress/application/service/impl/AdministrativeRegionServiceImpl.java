package com.sonnguyen.snaddress.application.service.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionSearchRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionUpdateRequest;
import com.sonnguyen.snaddress.application.mapper.AddressMapper;
import com.sonnguyen.snaddress.application.service.AdministrativeRegionService;
import com.sonnguyen.snaddress.domain.AdministrativeRegion;
import com.sonnguyen.snaddress.domain.cmd.AdministrativeRegionCreateCmd;
import com.sonnguyen.snaddress.domain.cmd.AdministrativeRegionUpdateCmd;
import com.sonnguyen.snaddress.domain.query.AdministrativeRegionSearchQuery;
import com.sonnguyen.snaddress.domain.repository.AdministrativeRegionRepository;
import com.sonnguyen.snaddress.domain.repository.CountryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class AdministrativeRegionServiceImpl implements AdministrativeRegionService {
    AdministrativeRegionRepository regionRepository;
    CountryRepository countryRepository;
    AddressMapper addressMapper;

    @Override
    public AdministrativeRegion create(AdministrativeRegionCreateRequest request) {
        // validate country exists
        this.countryRepository.getById(request.getCountryId());

        // validate parent exists if provided
        if (request.getParentId() != null) {
            this.regionRepository.getById(request.getParentId());
        }

        AdministrativeRegionCreateCmd cmd = this.addressMapper.from(request);
        AdministrativeRegion region = new AdministrativeRegion(cmd);
        this.regionRepository.save(region);
        return region;
    }

    @Override
    public AdministrativeRegion update(UUID id, AdministrativeRegionUpdateRequest request) {
        AdministrativeRegion region = this.regionRepository.getById(id);

        // validate parent exists if provided
        if (request.getParentId() != null) {
            this.regionRepository.getById(request.getParentId());
        }

        AdministrativeRegionUpdateCmd cmd = this.addressMapper.from(request);
        region.update(cmd);
        this.regionRepository.save(region);
        return region;
    }

    @Override
    public void delete(UUID id) {
        AdministrativeRegion region = this.regionRepository.getById(id);
        region.delete();
        this.regionRepository.save(region);
    }

    @Override
    @Transactional(readOnly = true)
    public AdministrativeRegion getById(UUID id) {
        return this.regionRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PagingResponse<AdministrativeRegion> search(AdministrativeRegionSearchRequest request) {
        AdministrativeRegionSearchQuery query = this.addressMapper.from(request);
        long count = this.regionRepository.count(query);
        if (count == 0) {
            return PagingResponse.of(List.of(), 0L, query.getPageSize(), request.getPageIndex());
        }
        List<AdministrativeRegion> regions = this.regionRepository.search(query);
        return PagingResponse.of(regions, count, request.getPageSize(), request.getPageIndex());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdministrativeRegion> findByParentId(UUID parentId) {
        return this.regionRepository.findByParentId(parentId);
    }
}

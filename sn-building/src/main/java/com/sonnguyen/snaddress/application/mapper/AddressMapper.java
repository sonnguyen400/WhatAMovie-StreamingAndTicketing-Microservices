package com.sonnguyen.snaddress.application.mapper;

import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionSearchRequest;
import com.sonnguyen.snaddress.application.dto.request.AdministrativeRegionUpdateRequest;
import com.sonnguyen.snaddress.application.dto.request.CountryCreateRequest;
import com.sonnguyen.snaddress.application.dto.request.CountrySearchRequest;
import com.sonnguyen.snaddress.application.dto.request.CountryUpdateRequest;
import com.sonnguyen.snaddress.domain.cmd.AdministrativeRegionCreateCmd;
import com.sonnguyen.snaddress.domain.cmd.AdministrativeRegionUpdateCmd;
import com.sonnguyen.snaddress.domain.cmd.CountryCreateCmd;
import com.sonnguyen.snaddress.domain.cmd.CountryUpdateCmd;
import com.sonnguyen.snaddress.domain.query.AdministrativeRegionSearchQuery;
import com.sonnguyen.snaddress.domain.query.CountrySearchQuery;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    CountryCreateCmd from(CountryCreateRequest request);

    CountryUpdateCmd from(CountryUpdateRequest request);

    CountrySearchQuery from(CountrySearchRequest request);

    AdministrativeRegionCreateCmd from(AdministrativeRegionCreateRequest request);

    AdministrativeRegionUpdateCmd from(AdministrativeRegionUpdateRequest request);

    AdministrativeRegionSearchQuery from(AdministrativeRegionSearchRequest request);
}

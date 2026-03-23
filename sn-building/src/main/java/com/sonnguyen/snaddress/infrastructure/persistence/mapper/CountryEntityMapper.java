package com.sonnguyen.snaddress.infrastructure.persistence.mapper;

import com.sonnguyen.common.data.persistence.mapper.EntityMapper;
import com.sonnguyen.snaddress.domain.Country;
import com.sonnguyen.snaddress.infrastructure.persistence.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryEntityMapper extends EntityMapper<Country, CountryEntity> {
}

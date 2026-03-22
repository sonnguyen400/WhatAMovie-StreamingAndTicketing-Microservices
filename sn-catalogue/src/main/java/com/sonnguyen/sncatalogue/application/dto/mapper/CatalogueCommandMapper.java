package com.sonnguyen.sncatalogue.application.dto.mapper;


import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemCreateUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.CatalogItemSearchRequest;
import com.sonnguyen.sncatalogue.application.dto.request.MovieCreateUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.PersonCreateOrUpdateRequest;
import com.sonnguyen.sncatalogue.application.dto.request.PersonSearchRequest;
import com.sonnguyen.sncatalogue.domain.command.CatalogItemCreateOrUpdateCmd;
import com.sonnguyen.sncatalogue.domain.command.MovieCreateOrUpdateCmd;
import com.sonnguyen.sncatalogue.domain.command.PersonCreateUpdateCmd;
import com.sonnguyen.sncatalogue.domain.query.CatalogItemSearchQuery;
import com.sonnguyen.sncatalogue.domain.query.PersonSearchQuery;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CatalogueCommandMapper {
    CatalogItemCreateOrUpdateCmd from(CatalogItemCreateUpdateRequest request);

    MovieCreateOrUpdateCmd from(MovieCreateUpdateRequest request);

    PersonCreateUpdateCmd from(PersonCreateOrUpdateRequest request);

    PersonSearchQuery from(PersonSearchRequest request);

    CatalogItemSearchQuery from(CatalogItemSearchRequest request);
}

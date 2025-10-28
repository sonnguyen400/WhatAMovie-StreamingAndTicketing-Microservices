package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.sncatalogue.infrastructure.constant.PersonRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CatalogPartnerCreateOrUpdateRequest {
    private UUID personId;
    private PersonRole role;
}

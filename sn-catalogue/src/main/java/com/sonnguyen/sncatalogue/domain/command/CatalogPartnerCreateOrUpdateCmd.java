package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.sncatalogue.infrastructure.constant.PersonRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CatalogPartnerCreateOrUpdateCmd {
    private UUID personId;
    private PersonRole role;
}

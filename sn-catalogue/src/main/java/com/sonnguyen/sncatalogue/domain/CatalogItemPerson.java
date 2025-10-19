package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.sncatalogue.infrastructure.constant.PersonRole;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
public class CatalogItemPerson extends AuditingDomain {
    private UUID id;
    private UUID personId;
    private UUID catalogId;
    private PersonRole role;
}

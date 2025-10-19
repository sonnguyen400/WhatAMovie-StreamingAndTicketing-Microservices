package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
public class Tag extends AuditingDomain {
    private UUID id;
}

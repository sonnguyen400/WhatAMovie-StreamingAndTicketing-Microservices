package com.sonnguyen.snsystem.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;

import java.util.UUID;

public class DomainChangelog extends AuditingDomain {
    private UUID id;
    private UUID domainId;
    private String domainData;
    private Integer version;
}

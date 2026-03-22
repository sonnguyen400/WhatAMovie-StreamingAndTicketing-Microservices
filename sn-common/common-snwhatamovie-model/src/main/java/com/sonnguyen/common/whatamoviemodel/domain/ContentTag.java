package com.sonnguyen.common.whatamoviemodel.domain;


import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ContentTag extends AuditingDomain {
    private UUID id;
    private UUID domainId;
    private UUID tagId;
    private DomainType domainType;
    private Boolean deleted;

    public ContentTag() {
    }

    public ContentTag(UUID tagId, UUID domainId, DomainType domainType) {
        this.domainId = domainId;
        this.tagId = tagId;
        this.domainType = domainType;
    }
}

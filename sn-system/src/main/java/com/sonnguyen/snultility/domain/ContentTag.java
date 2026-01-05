package com.sonnguyen.snultility.domain;


import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.util.IdUtils;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
public class ContentTag extends AuditingDomain {
    private UUID id;
    private UUID domainId;
    private UUID tagId;
    private DomainType domainType;
    private Boolean deleted;

    public ContentTag(UUID tagId, UUID domainId,DomainType domainType) {
        this.id = IdUtils.nextId();
        this.domainId = domainId;
        this.tagId = tagId;
        this.domainType = domainType;
    }

    public void delete() {
        this.deleted = true;
    }

    public void unDelete() {
        this.deleted = false;
    }
}

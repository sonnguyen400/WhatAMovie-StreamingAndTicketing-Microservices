package com.sonnguyen.sncatalogue.domain;


import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
public class ContentTag extends AuditingDomain {
    private UUID id;
    private UUID contentId;
    private UUID tagId;
}

package com.sonnguyen.snultility.domain.cmd;

import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class TagAssignmentCmd {
    private List<UUID> tagIds;
    private DomainType domainType;
    private UUID domainId;
}

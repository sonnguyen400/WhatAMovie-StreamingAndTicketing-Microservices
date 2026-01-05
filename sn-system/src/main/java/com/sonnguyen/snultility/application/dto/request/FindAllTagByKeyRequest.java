package com.sonnguyen.snultility.application.dto.request;

import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class FindAllTagByKeyRequest {
    private List<UUID> ids;
    private List<UUID> domainId;
    private List<DomainType> domainTypes;
}

package com.sonnguyen.snultility.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class FindAllTagByKeyRequest extends Request {
    private List<UUID> ids;
    private List<UUID> domainId;
    private List<DomainType> domainTypes;
}

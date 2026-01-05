package com.sonnguyen.snultility.application.dto.request;

import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class TagAssignmentRequest {
    @NotEmpty
    @NotNull
    private List<UUID> tagIds;
    @NotNull
    private DomainType domainType;
    @NotNull
    private UUID domainId;
}

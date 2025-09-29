package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class Permission extends AuditingDomain {
    private UUID id;
    private String name;
    private String code;
    private Boolean deleted;
}

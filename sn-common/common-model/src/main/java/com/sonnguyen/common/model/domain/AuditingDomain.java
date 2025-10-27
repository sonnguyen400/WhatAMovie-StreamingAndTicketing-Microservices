package com.sonnguyen.common.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class AuditingDomain implements Serializable {
    private String createdBy;
    private String lastModifiedBy;
    private Instant createdAt;
    private Instant lastModifiedAt;
}

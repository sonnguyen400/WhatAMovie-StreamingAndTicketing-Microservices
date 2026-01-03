package com.sonnguyen.snstorage.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;
import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.snstorage.infrastructure.persistence.entity.FileVariantEntity}
 */
@Getter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class FileVariant extends AuditingDomain {
    private UUID id;
    private UUID fileId;
    private Boolean isOriginal;
    private Boolean isThumbnail;
    private String resolution;
    private BigInteger sizeBytes;
    private String name;
    private String extension;
}

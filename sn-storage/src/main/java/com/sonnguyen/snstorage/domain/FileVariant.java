package com.sonnguyen.snstorage.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;

import java.math.BigInteger;
import java.util.UUID;

public class FileVariant extends AuditingDomain {
    private UUID id;
    private UUID fileId;
    private Boolean isOriginal;
    private String resolution;
    private BigInteger sizeBytes;
}

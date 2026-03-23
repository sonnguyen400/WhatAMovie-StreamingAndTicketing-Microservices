package com.sonnguyen.snstorage.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snstorage.domain.cmd.FileVariantCmd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FileVariant extends AuditingDomain {
    private UUID id;
    private UUID fileId;
    private Boolean isOriginal;
    private Boolean isThumbnail;
    private BigInteger sizeBytes;
    private Mimetype mimetype;
    private Integer accessCounter;
    private FileMetaData metaData;
    private String externalUrl;
    private String externalId;
    private Integer version;

    public FileVariant(File file, FileVariantCmd cmd) {
        this.id = IdUtils.nextId();
        this.fileId = file.getId();
        this.isOriginal = cmd.getIsOriginal();
        this.isThumbnail = cmd.getIsThumbnail();
        this.sizeBytes = cmd.getSizeBytes();
        this.mimetype = cmd.getMimetype();
        this.accessCounter = cmd.getAccessCounter();
        this.metaData = cmd.getMetaData();
        this.externalUrl = cmd.getExternalUrl();
        this.externalId = cmd.getExternalId();
    }
}

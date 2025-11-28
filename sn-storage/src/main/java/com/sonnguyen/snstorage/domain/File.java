package com.sonnguyen.snstorage.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.sonnguyen.snstorage.infrastructure.persistence.entity.FileEntity}
 */
@Getter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class File extends AuditingDomain {
    private UUID id;
    private String originalName;
    private String name;
    private String extension;
    private Mimetype mimetype;
    private Integer accessCounter;
    private StorageProvider storage;
    private Integer version;
    private List<FileVersion> versions;
}

package com.sonnguyen.snstorage.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import lombok.Getter;

import java.util.UUID;

@Getter
public class File extends AuditingDomain {
    private UUID id;
    private String originalName;
    private Mimetype mimetype;
    private
    private Integer accessCounter;
}

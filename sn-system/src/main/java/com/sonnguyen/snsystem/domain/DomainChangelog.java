package com.sonnguyen.snsystem.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DomainChangelog extends AuditingDomain {
    private UUID id;
    private UUID domainId;
    private String domainData;
    private Integer version;
}

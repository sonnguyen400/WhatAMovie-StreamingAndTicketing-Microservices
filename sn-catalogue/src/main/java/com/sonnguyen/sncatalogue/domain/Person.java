package com.sonnguyen.sncatalogue.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@SuperBuilder
public class Person extends AuditingDomain {
    private UUID id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String description;
    private PersonMetaData metaData;
}

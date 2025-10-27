package com.sonnguyen.sncatalogue.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.sncatalogue.infrastructure.constant.PersonRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@MappedSuperclass
public class CatalogItemPersonEntity extends AuditingEntity {
    @Id
    private UUID id;

    @Column(name = "person_id", nullable = false)
    private UUID personId;

    @Column(name = "catalog_id", nullable = false)
    private UUID catalogId;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonRole role;
}

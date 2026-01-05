package com.sonnguyen.snultility.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
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
public class ContentTagEntity extends AuditingEntity {
    @Id
    private UUID id;

    @Column(name = "domain_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DomainType domainType;

    @Column(name = "domain_id", nullable = false)
    private UUID domainId;

    @Column(name = "tag_id", nullable = false)
    private UUID tagId;

    @Column(name = "deleted")
    private Boolean deleted;
}

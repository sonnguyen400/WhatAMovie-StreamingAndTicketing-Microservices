package com.sonnguyen.snstorage.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "file_variant_entity")
public class FileVariantEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "is_original")
    private Boolean isOriginal;

    @Column(name = "is_thumbnail")
    private Boolean isThumbnail;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "size_bytes")
    private BigInteger sizeBytes;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "external_id")
    private String externalId;
}

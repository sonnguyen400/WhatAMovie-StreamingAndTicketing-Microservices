package com.sonnguyen.snstorage.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "file_entity")
public class FileEntity extends AuditingEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "mimetype")
    @Enumerated(EnumType.STRING)
    private Mimetype mimetype;

    @Column(name = "access_counter")
    private Integer accessCounter;

    @Column(name = "storage")
    @Enumerated(EnumType.STRING)
    private StorageProvider storage;

    @Column(name = "version")
    @Version
    private Integer version;
}

package com.sonnguyen.snstorage.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.AuditingEntity;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.snstorage.domain.FileMetaData;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "file_variant_entity")
public class FileVariantEntity extends AuditingEntity {

    @Id
    private UUID id;

    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "is_original")
    private Boolean isOriginal;

    @Column(name = "is_thumbnail")
    private Boolean isThumbnail;

    @Column(name = "size_bytes")
    private BigInteger sizeBytes;

    @Column(name = "mimetype")
    private Mimetype mimetype;

    @Column(name = "access_counter")
    private Integer accessCounter;

    @Column(name = "metadata")
    private FileMetaData metaData;

    @Column(name = "external_url")
    private String externalUrl;

    @Column(name = "external_id")
    private String externalId;

    @Version
    @Column(name = "version")
    private Integer version;

}

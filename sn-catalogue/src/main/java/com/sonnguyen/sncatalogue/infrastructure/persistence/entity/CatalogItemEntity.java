package com.sonnguyen.sncatalogue.infrastructure.persistence.entity;

import com.sonnguyen.common.data.persistence.entity.PolicyResourceEntity;
import com.sonnguyen.common.util.Validator;
import com.sonnguyen.sncatalogue.domain.CatalogMetadata;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Entity
@MappedSuperclass
public class CatalogItemEntity extends PolicyResourceEntity {
    @Id
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "content_type", length = Validator.Length.ENUM_MAX_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(name = "status", length = Validator.Length.ENUM_MAX_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "metadata")
    private CatalogMetadata metadata;

    @Column(name = "distribution_channel", length = Validator.Length.ENUM_MAX_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private DistributionChannel distributionChannel;

    @Column(name = "parent_id")
    private UUID parentId;

    @Version
    private Integer version;
}

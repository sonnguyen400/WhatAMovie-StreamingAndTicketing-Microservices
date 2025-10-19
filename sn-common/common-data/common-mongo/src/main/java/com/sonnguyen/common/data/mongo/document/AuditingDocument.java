package com.sonnguyen.common.data.mongo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Setter
@Getter
public abstract class AuditingDocument {
    @CreatedBy
    @Field(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Field(name = "last_modified_by")
    private String lastModifiedBy;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Field(name = "last_modified_at")
    private Instant lastModifiedAt;
}

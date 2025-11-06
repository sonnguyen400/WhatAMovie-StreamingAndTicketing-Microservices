package com.sonnguyen.common.data.mongo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
public abstract class TenancyDocument extends AuditingDocument{
    @Field("tenant_id")
    private String tenantId;
}

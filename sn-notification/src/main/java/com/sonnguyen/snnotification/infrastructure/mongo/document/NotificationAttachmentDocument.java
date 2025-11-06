package com.sonnguyen.snnotification.infrastructure.mongo.document;

import com.sonnguyen.common.data.mongo.document.AuditingDocument;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import jakarta.persistence.Enumerated;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
public class NotificationAttachmentDocument extends AuditingDocument {
    @Field(name = "file_id")
    private UUID fileId;

    @Field(name = "file_name")
    private String fileName;

    @Field(name = "mime_type")
    @Enumerated
    private Mimetype mimetype;

    @Field(name = "size_kb")
    private Float sizeInKiloBytes;
}

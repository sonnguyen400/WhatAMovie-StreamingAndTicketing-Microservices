package com.sonnguyen.snnotification.infrastructure.mongo.document;

import com.sonnguyen.common.data.mongo.document.AuditingDocument;
import com.sonnguyen.common.model.infrastructure.constant.NotificationRecipientType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;
import java.util.UUID;

@Document(collection = "notification_recipients")
public class NotificationRecipientDocument extends AuditingDocument {
    @Id
    private UUID id;

    @Field(name = "user_id")
    private UUID userId;

    @Field(name = "address")
    private String address;

    @Field(name = "metadata")
    private Map<String, Object> metadata;

    @Field(name = "recipient_type")
    @Enumerated
    private NotificationRecipientType recipientType;
}

package com.sonnguyen.snnotification.infrastructure.mongo.document;

import com.sonnguyen.common.data.mongo.document.AuditingDocument;
import com.sonnguyen.common.model.infrastructure.constant.NotificationChanel;
import com.sonnguyen.common.model.infrastructure.constant.NotificationDeliveryStatus;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "notification_deliveries")
public class NotificationDeliveryDocument extends AuditingDocument {
    @Id
    private UUID id;

    @Field(name = "chanel")
    private NotificationChanel chanel;

    @Field(name = "delivered")
    private Boolean delivered;

    @Field(name = "status")
    @Enumerated
    private NotificationDeliveryStatus status;

    @Field(name = "scheduled_at")
    private Instant scheduledAt;

    @Field(name = "last_attempt_at")
    private Instant lastAttemptAt;

    @Field(name = "attempts")
    private Integer attempts;

    @Field(name = "address")
    private String address;

    @Field(name = "recipient_id")
    private UUID recipientId;

    @Field(name = "notification_id")
    private UUID notificationId;

    @Field(name = "recipient")
    private NotificationRecipientDocument recipient;
}

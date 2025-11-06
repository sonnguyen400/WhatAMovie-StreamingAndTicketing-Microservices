package com.sonnguyen.snnotification.infrastructure.mongo.document;

import com.sonnguyen.common.data.mongo.document.AuditingDocument;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationContentType;
import com.sonnguyen.snnotification.infrastructure.constants.NotificationTemplateStatus;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Document(collection = "notification_template")
public class NotificationTemplateDocument extends AuditingDocument {
    @Id
    private UUID id;

    @Field(name = "title")
    private String title;

    @Field(name = "priority")
    private Integer priority;

    @Field(name = "metadata")
    private Map<String, Object> metadata;

    @Field(name = "template_vars")
    private Map<String, Object> templateVars;

    @Field(name = "scheduled_at")
    private Instant scheduleAt;

    @Field(name = "content")
    private String content;

    @Field(name = "content_type")
    @Enumerated
    private NotificationContentType contentType;

    @Field(name = "attachments")
    private List<NotificationAttachmentDocument> attachments;

    @Field(name = "start_time")
    private Instant startTime;

    @Field(name = "end_time")
    private Instant endTime;

    @Field(name = "status")
    private NotificationTemplateStatus status;

    @Field(name = "cron_expression")
    private String cron_expression;
}

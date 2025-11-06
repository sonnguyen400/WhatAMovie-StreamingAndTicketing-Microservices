package com.sonnguyen.snnotification.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationContentType;
import com.sonnguyen.snnotification.infrastructure.constants.NotificationTemplateStatus;
import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationAttachmentDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class NotificationTemplate extends AuditingDomain {
    private UUID id;
    private String title;
    private Integer priority;
    private Map<String, Object> metadata;
    private Map<String, Object> templateVars;
    private Instant scheduleAt;
    private String content;
    private NotificationContentType contentType;
    private List<NotificationAttachmentDocument> attachments;
    private Instant startTime;
    private Instant endTime;
    private NotificationTemplateStatus status;
    private String cron_expression;
}

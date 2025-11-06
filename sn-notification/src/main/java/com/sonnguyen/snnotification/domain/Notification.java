package com.sonnguyen.snnotification.domain;

import com.sonnguyen.common.model.application.response.iam.UserDTO;
import com.sonnguyen.common.model.application.response.storage.FileDTO;
import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationChanel;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationContentType;
import com.sonnguyen.snnotification.domain.cmd.NotificationAttachmentCreateCmd;
import com.sonnguyen.snnotification.domain.cmd.NotificationCreateCmd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class Notification extends AuditingDomain {
    private UUID id;
    private Long templateId;
    private String title;
    private Integer priority;
    private Map<String, Object> metadata;
    private Map<String, Object> templateVars;
    private Instant scheduleAt;
    private String content;
    private NotificationContentType contentType;
    private List<NotificationAttachment> attachments;
    private List<NotificationChanel> chanel;
    private List<NotificationRecipient> recipients;

    //Enrich Delivery
    private List<NotificationDelivery> deliveries;

    public Notification(NotificationCreateCmd cmd) {
        this.id = UUID.randomUUID();
        this.title = cmd.getTitle();
        this.priority = cmd.getPriority();
        this.metadata = cmd.getMetadata();
        this.templateVars = cmd.getTemplateVars();
        this.content = cmd.getContent();
        this.contentType = cmd.getContentType();
        this.chanel = cmd.getChanel();
        if (Objects.nonNull(cmd.getScheduleAt())) {
            this.scheduleAt = cmd.getScheduleAt();
        } else {
            this.scheduleAt = Instant.now();
        }
        this.updateAttachments(cmd.getAttachments());
        this.updateRecipient(cmd);
    }

    public void updateAttachments(List<FileDTO> fileDTOS) {
        if (Objects.isNull(this.attachments)) {
            this.attachments = new ArrayList<>();
        }
        List<UUID> newFileIds = fileDTOS.stream().map(FileDTO::getId).toList();
        this.attachments.removeIf(it -> !newFileIds.contains(it.getFileId()));
        for (FileDTO file : fileDTOS) {
            if (this.attachments.stream().noneMatch(it -> it.getFileId().equals(file.getId()))) {
                NotificationAttachmentCreateCmd attachmentCreateCmd = new NotificationAttachmentCreateCmd(file);
                this.attachments.add(new NotificationAttachment(attachmentCreateCmd));
            }
        }
    }

    public void updateRecipient(NotificationCreateCmd cmd) {
        List<UserDTO> users = Objects.nonNull(cmd.getUsers()) ? cmd.getUsers() : new ArrayList<>();
        List<String> emails = Objects.nonNull(cmd.getEmails()) ? cmd.getEmails() : new ArrayList<>();
        List<String> phoneNumbers = Objects.nonNull(cmd.getPhoneNumbers()) ? cmd.getPhoneNumbers() : new ArrayList<>();
        List<NotificationRecipient> notificationRecipients = new ArrayList<>();
        for (UserDTO user : users) {
            NotificationRecipient recipient = new NotificationRecipient(user, this);
            notificationRecipients.add(recipient);
        }
        for (String email : emails) {
            NotificationRecipient recipient = new NotificationRecipient(email, this, NotificationChanel.EMAIL);
            notificationRecipients.add(recipient);
        }
        for (String phoneNumber : phoneNumbers) {
            NotificationRecipient recipient = new NotificationRecipient(phoneNumber, this, NotificationChanel.SMS);
            notificationRecipients.add(recipient);
        }
        this.recipients = notificationRecipients;
        this.deliveries = notificationRecipients.stream().flatMap(it -> it.getDeliveries().stream()).toList();
    }
}

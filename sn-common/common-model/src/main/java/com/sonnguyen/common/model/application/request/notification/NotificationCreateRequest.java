package com.sonnguyen.common.model.application.request.notification;

import com.sonnguyen.common.model.application.response.iam.UserDTO;
import com.sonnguyen.common.model.application.response.storage.FileDTO;
import com.sonnguyen.common.model.infrastructure.constant.NotificationChanel;
import com.sonnguyen.common.model.infrastructure.constant.NotificationContentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
public class NotificationCreateRequest {
    private Long templateId;

    @NotBlank(message = "TITLE_REQUIRED")
    private String title;

    private Integer priority;

    private Map<String, Object> metadata;

    private Map<String, Object> templateVars;

    private Instant scheduleAt;

    @NotBlank(message = "CONTENT_REQUIRED")
    private String content;

    @NotBlank(message = "CoNTENT_TYPE_REQUIRED")
    private NotificationContentType contentType;

    private List<FileDTO> attachments;

    private List<UserDTO> users;
    private List<String> emails;
    private List<String> phoneNumbers;

    @NotEmpty(message = "CHANEL_REQUIRED")
    private List<NotificationChanel> chanel;
}

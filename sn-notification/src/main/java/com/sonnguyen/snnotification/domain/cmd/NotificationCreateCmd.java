package com.sonnguyen.snnotification.domain.cmd;

import com.sonnguyen.common.model.application.response.iam.UserDTO;
import com.sonnguyen.common.model.application.response.storage.FileDTO;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationChanel;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationContentType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class NotificationCreateCmd {
    private Long templateId;
    private String title;
    private Integer priority;
    private Map<String, Object> metadata;
    private Map<String, Object> templateVars;
    private Instant scheduleAt;
    private String content;
    private NotificationContentType contentType;
    private List<FileDTO> attachments;
    private List<UserDTO> users;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<NotificationChanel> chanel;
}

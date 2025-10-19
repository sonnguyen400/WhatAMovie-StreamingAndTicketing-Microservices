package com.sonnguyen.snnotification.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailRequestResult {
    private boolean isSuccess;
    private String message;
    private String email;
}

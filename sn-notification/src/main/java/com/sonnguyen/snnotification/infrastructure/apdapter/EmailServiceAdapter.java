package com.sonnguyen.snnotification.infrastructure.apdapter;

import com.sonnguyen.snnotification.application.dto.request.MailBuilder;
import com.sonnguyen.snnotification.application.dto.response.MailRequestResult;

public interface EmailServiceAdapter {
    MailRequestResult sendEmail(MailBuilder builder);
}

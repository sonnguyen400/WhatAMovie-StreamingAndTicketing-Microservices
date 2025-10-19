package com.sonnguyen.snnotification.infrastructure.apdapter.impl;

import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import com.sonnguyen.snnotification.infrastructure.configuration.ApplicationConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class SendgridConfig {
    ApplicationConfiguration applicationConfiguration;

    @Bean
    public SendGrid getSendgridConfig() {
        return new SendGrid(applicationConfiguration.getAdapter().getEmail().getSendGrid().getApiKey());
    }

    @Bean
    public Email getMailConfig() {
        return new Email(
                applicationConfiguration.getAdapter().getEmail().getSendGrid().getSourceEmail(),
                applicationConfiguration.getAdapter().getEmail().getSendGrid().getSourceName()
        );
    }
}

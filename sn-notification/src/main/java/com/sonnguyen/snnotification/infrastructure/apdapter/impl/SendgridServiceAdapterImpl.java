package com.sonnguyen.snnotification.infrastructure.apdapter.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sonnguyen.common.model.infrastructure.support.constant.HttpStatus;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.snnotification.application.dto.request.MailBuilder;
import com.sonnguyen.snnotification.application.dto.response.MailRequestResult;
import com.sonnguyen.snnotification.infrastructure.apdapter.EmailServiceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class SendgridServiceAdapterImpl implements EmailServiceAdapter {
    private static final String EMAIL_ENDPOINT = "mail/send";
    SendGrid sendGrid;
    Email from;

    @Override
    public MailRequestResult sendEmail(MailBuilder builder) {
        List<Attachments> attachments = this.createAttachment(builder.getAttachments());
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(builder.getTitle());
        mail.addContent(new Content(builder.getMimetype().getValue(), builder.getContent()));
        attachments.forEach(mail::addAttachments);
        //Set emails
        Personalization personalization = new Personalization();
        builder.getEmails().forEach(it -> personalization.addTo(new Email(it)));
        builder.getBccEmails().forEach(it -> personalization.addTo(new Email(it)));
        builder.getBccEmails().forEach(it -> personalization.addTo(new Email(it)));
        mail.addPersonalization(personalization);
        Request request = new Request();
        MailRequestResult result;
        try {
            request.setBody(mail.build());
            request.setEndpoint(EMAIL_ENDPOINT);
            request.setMethod(Method.POST);
            Response response = sendGrid.api(request);
            if (Objects.equals(response.getStatusCode(), HttpStatus.OK)) {
                result = MailRequestResult.builder()
                        .message(response.getBody())
                        .isSuccess(true)
                        .build();
            } else {
                result = MailRequestResult.builder()
                        .message(response.getBody())
                        .isSuccess(false)
                        .build();
            }
        } catch (IOException e) {
            result = MailRequestResult.builder()
                    .message(e.getMessage())
                    .isSuccess(false)
                    .build();
        }
        log.info("Sendgrid mail request fail due to {}", result.getMessage());
        return result;
    }

    private List<Attachments> createAttachment(List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) return new ArrayList<>();
        List<Attachments> attachments = new ArrayList<>();
        for (MultipartFile file : files) {
            byte[] encodedFileContent = new byte[0];
            try {
                encodedFileContent = Base64.getEncoder().encode(file.getBytes());
            } catch (IOException e) {
                continue;
            }
            Attachments attachment = new Attachments();
            attachment.setDisposition("attachment");
            attachment.setType(file.getContentType());
            attachment.setFilename(file.getOriginalFilename());
            attachment.setContent(new String(encodedFileContent, StandardCharsets.UTF_8));
            attachments.add(attachment);
        }
        return attachments;
    }
}

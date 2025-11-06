package com.sonnguyen.snnotification.application.dto.request;

import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class MailBuilder {
    public List<String> emails;
    public List<String> ccEmails;
    public List<String> bccEmails;
    public String title;
    public String content;
    public Mimetype mimetype;
    public List<MultipartFile> attachments;
}

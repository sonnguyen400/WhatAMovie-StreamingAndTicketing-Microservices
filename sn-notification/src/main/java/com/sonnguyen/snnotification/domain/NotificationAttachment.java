package com.sonnguyen.snnotification.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.snnotification.domain.cmd.NotificationAttachmentCreateCmd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class NotificationAttachment extends AuditingDomain {
    private UUID fileId;
    private String fileName;
    private Mimetype mimetype;
    private Float sizeInKiloBytes;

    //Enrich
    private MultipartFile data;

    public NotificationAttachment(NotificationAttachmentCreateCmd cmd) {
        this.fileId = cmd.getFileId();
        this.fileName = cmd.getFileName();
        this.mimetype = cmd.getMimetype();
        this.sizeInKiloBytes = cmd.getSizeInKiloBytes();
    }

    public void enrichData(MultipartFile file) {
        this.data = file;
    }
}

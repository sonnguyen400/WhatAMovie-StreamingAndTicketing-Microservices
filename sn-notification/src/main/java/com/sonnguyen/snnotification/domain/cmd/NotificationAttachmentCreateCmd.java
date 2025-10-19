package com.sonnguyen.snnotification.domain.cmd;

import com.sonnguyen.common.model.application.response.storage.FileDTO;
import com.sonnguyen.common.model.infrastructure.constant.Mimetype;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class NotificationAttachmentCreateCmd {
    private UUID fileId;
    private String fileName;
    private Mimetype mimetype;
    private Float sizeInKiloBytes;

    public NotificationAttachmentCreateCmd(FileDTO fileDTO) {
        this.fileId = fileDTO.getId();
        this.fileName = fileDTO.getOriginalName();
        this.mimetype = fileDTO.getMimetype();
        this.sizeInKiloBytes = fileDTO.getSizeInKiloBytes();
    }
}

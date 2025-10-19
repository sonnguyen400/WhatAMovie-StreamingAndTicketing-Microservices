package com.sonnguyen.common.model.application.response.storage;

import com.sonnguyen.common.model.infrastructure.constant.Mimetype;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FileDTO {
    private UUID id;
    private String name;
    private String originalName;
    private String viewUrl;
    private Mimetype mimetype;
    private Float sizeInKiloBytes;
}

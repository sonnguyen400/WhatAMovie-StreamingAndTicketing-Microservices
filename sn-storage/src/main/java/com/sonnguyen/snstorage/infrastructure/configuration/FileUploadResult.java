package com.sonnguyen.snstorage.infrastructure.configuration;

import lombok.Builder;

@Builder
public class FileUploadResult {
    private String externalId;
    private String url;
}

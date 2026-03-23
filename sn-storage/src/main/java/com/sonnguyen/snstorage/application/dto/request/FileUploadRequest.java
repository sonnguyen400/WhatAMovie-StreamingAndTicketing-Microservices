package com.sonnguyen.snstorage.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class FileUploadRequest extends Request {
    @Builder.Default
    private String bucketName = "default";
}

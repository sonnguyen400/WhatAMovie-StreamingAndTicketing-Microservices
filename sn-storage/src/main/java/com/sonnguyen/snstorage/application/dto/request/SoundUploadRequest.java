package com.sonnguyen.snstorage.application.dto.request;

import com.sonnguyen.snstorage.infrastructure.support.constant.SoundDefinition;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class SoundUploadRequest extends FileUploadRequest {
    private List<SoundDefinition> resolutions;
    private List<SoundDefinition> formats;
}

package com.sonnguyen.snstorage.application.dto.request;

import com.sonnguyen.snstorage.infrastructure.support.constant.ImageColorMode;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class ImageUploadRequest extends FileUploadRequest {
    private List<ImageColorMode> colorModes;
    private List<ImageFormat> formats;
    private List<VisualDefinition> resolutions;
}

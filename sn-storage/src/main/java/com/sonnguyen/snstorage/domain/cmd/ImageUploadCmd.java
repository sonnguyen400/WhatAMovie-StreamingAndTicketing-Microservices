package com.sonnguyen.snstorage.domain.cmd;

import com.sonnguyen.snstorage.infrastructure.support.constant.ImageColorMode;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import lombok.Data;

import java.util.List;

@Data
public class ImageUploadCmd extends FileUploadCmd {
    private List<ImageColorMode> colorModes;
    private List<ImageFormat> formats;
    private List<VisualDefinition> resolutions;
}

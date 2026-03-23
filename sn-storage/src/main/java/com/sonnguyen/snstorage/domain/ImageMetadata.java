package com.sonnguyen.snstorage.domain;

import com.sonnguyen.snstorage.infrastructure.support.constant.ImageColorMode;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageMetadata extends FileMetaData {
    private ImageColorMode colorMode;
    private ImageFormat format;
    private VisualDefinition resolution;
    private Integer width;
    private Integer height;
}
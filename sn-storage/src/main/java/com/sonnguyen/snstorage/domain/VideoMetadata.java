package com.sonnguyen.snstorage.domain;

import com.sonnguyen.snstorage.infrastructure.support.constant.VideoBitrateLevel;
import com.sonnguyen.snstorage.infrastructure.support.constant.VideoCodec;
import com.sonnguyen.snstorage.infrastructure.support.constant.VideoFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoMetadata extends FileMetaData {
    private VisualDefinition resolution;
    private VideoFormat format;
    private VideoCodec codec;
    private VideoBitrateLevel bitrateLevel;
}

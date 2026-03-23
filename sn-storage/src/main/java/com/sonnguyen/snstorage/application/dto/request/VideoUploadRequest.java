package com.sonnguyen.snstorage.application.dto.request;

import com.sonnguyen.snstorage.infrastructure.support.constant.VideoBitrateLevel;
import com.sonnguyen.snstorage.infrastructure.support.constant.VideoCodec;
import com.sonnguyen.snstorage.infrastructure.support.constant.VideoFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class VideoUploadRequest extends FileUploadRequest {
    private List<VisualDefinition> resolutions;
    private List<VideoFormat> formats;
    private List<VideoCodec> codecs;
    private List<VideoBitrateLevel> bitrateLevels;
}

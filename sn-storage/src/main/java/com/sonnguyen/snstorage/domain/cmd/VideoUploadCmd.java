package com.sonnguyen.snstorage.domain.cmd;

import com.sonnguyen.snstorage.infrastructure.support.constant.VideoBitrateLevel;
import com.sonnguyen.snstorage.infrastructure.support.constant.VideoCodec;
import com.sonnguyen.snstorage.infrastructure.support.constant.VideoFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import lombok.Data;

import java.util.List;


@Data
public class VideoUploadCmd extends FileUploadCmd {
    private List<VisualDefinition> resolutions;
    private List<VideoFormat> formats;
    private List<VideoCodec> codecs;
    private List<VideoBitrateLevel> bitrateLevels;
}

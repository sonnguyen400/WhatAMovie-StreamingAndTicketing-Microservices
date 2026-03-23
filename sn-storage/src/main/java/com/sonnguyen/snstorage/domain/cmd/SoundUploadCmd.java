package com.sonnguyen.snstorage.domain.cmd;

import com.sonnguyen.snstorage.infrastructure.support.constant.SoundDefinition;
import lombok.Data;

import java.util.List;

@Data
public class SoundUploadCmd extends FileUploadCmd {
    private List<SoundDefinition> resolutions;
    private List<SoundDefinition> formats;
}
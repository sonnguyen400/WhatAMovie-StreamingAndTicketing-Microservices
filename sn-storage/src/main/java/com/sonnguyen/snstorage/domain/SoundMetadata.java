package com.sonnguyen.snstorage.domain;

import com.sonnguyen.snstorage.infrastructure.support.constant.SoundDefinition;
import com.sonnguyen.snstorage.infrastructure.support.constant.SoundFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SoundMetadata extends FileMetaData {
    private SoundDefinition resolution;
    private SoundFormat format;
}

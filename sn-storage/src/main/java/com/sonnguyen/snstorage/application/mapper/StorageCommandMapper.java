package com.sonnguyen.snstorage.application.mapper;

import com.sonnguyen.snstorage.application.dto.request.FileUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.ImageUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.SoundUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.VideoUploadRequest;
import com.sonnguyen.snstorage.domain.cmd.FileUploadCmd;
import com.sonnguyen.snstorage.domain.cmd.ImageUploadCmd;
import com.sonnguyen.snstorage.domain.cmd.SoundUploadCmd;
import com.sonnguyen.snstorage.domain.cmd.VideoUploadCmd;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StorageCommandMapper {
    FileUploadCmd from(FileUploadRequest request);

    ImageUploadCmd from(ImageUploadRequest request);

    SoundUploadCmd from(SoundUploadRequest request);

    VideoUploadCmd from(VideoUploadRequest request);
}

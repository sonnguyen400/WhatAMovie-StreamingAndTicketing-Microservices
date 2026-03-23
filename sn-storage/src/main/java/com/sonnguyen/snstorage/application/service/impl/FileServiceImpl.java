package com.sonnguyen.snstorage.application.service.impl;

import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.common.util.FileUtils;
import com.sonnguyen.snstorage.application.dto.request.FileUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.ImageUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.SoundUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.VideoUploadRequest;
import com.sonnguyen.snstorage.application.mapper.StorageCommandMapper;
import com.sonnguyen.snstorage.application.service.FileService;
import com.sonnguyen.snstorage.domain.File;
import com.sonnguyen.snstorage.domain.cmd.ImageUploadCmd;
import com.sonnguyen.snstorage.infrastructure.adaper.StorageAdapter;
import com.sonnguyen.snstorage.infrastructure.adaper.StorageFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FileServiceImpl implements FileService {

    StorageFactory storageFactory;
    StorageCommandMapper storageCommandMapper;

    @Override
    public Response<File> uploadFile(MultipartFile file, FileUploadRequest request) {
        return null;
    }

    @Override
    @SneakyThrows
    public Response<File> uploadFile(MultipartFile fileData, ImageUploadRequest request) {
        StorageAdapter adapter = storageFactory.getDefault();
        String extension = FileUtils.getExtension(fileData.getOriginalFilename());
        Mimetype mimetype = FileUtils.getMimeType(extension);

        ImageUploadCmd imageUploadCmd = storageCommandMapper.from(request);
        File file = new File(fileData, imageUploadCmd, adapter.getProvider());
        return null;
    }

    @Override
    public Response<File> uploadFile(MultipartFile file, VideoUploadRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response<File> uploadFile(MultipartFile file, SoundUploadRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response<File> getById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Response<Void> deleteFileById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }
}

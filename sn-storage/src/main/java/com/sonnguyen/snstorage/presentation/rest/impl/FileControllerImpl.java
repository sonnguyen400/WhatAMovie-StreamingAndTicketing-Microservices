package com.sonnguyen.snstorage.presentation.rest.impl;

import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snstorage.application.dto.request.FileUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.ImageUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.SoundUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.VideoUploadRequest;
import com.sonnguyen.snstorage.application.service.FileService;
import com.sonnguyen.snstorage.domain.File;
import com.sonnguyen.snstorage.presentation.rest.FileController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FileControllerImpl implements FileController {

    FileService fileService;

    @Override
    public Response<File> uploadFile(MultipartFile file, FileUploadRequest request) {
        return this.fileService.uploadFile(file, request);
    }

    @Override
    public Response<File> uploadFile(MultipartFile file, VideoUploadRequest request) {
        return this.fileService.uploadFile(file, request);
    }

    @Override
    public Response<File> uploadFile(MultipartFile file, ImageUploadRequest request) {
        return this.fileService.uploadFile(file, request);
    }

    @Override
    public Response<File> uploadFile(MultipartFile file, SoundUploadRequest request) {
        return this.fileService.uploadFile(file, request);
    }

    @Override
    public Response<Void> deleteFileById(UUID id) {
        return this.fileService.deleteFileById(id);
    }

    @Override
    public Response<File> getById(UUID id) {
        return this.fileService.getById(id);
    }
}

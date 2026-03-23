package com.sonnguyen.snstorage.application.service;

import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snstorage.application.dto.request.FileUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.ImageUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.SoundUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.VideoUploadRequest;
import com.sonnguyen.snstorage.domain.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileService {

    Response<File> uploadFile(MultipartFile file, FileUploadRequest request);

    Response<File> uploadFile(MultipartFile file, ImageUploadRequest request);

    Response<File> uploadFile(MultipartFile file, VideoUploadRequest request);

    Response<File> uploadFile(MultipartFile file, SoundUploadRequest request);

    Response<File> getById(UUID id);

    Response<Void> deleteFileById(UUID id);
}

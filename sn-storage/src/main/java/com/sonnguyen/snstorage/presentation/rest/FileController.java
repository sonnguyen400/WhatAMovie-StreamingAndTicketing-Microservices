package com.sonnguyen.snstorage.presentation.rest;

import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.snstorage.application.dto.request.FileUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.ImageUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.SoundUploadRequest;
import com.sonnguyen.snstorage.application.dto.request.VideoUploadRequest;
import com.sonnguyen.snstorage.domain.File;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequestMapping("/api/v1/file")
public interface FileController {
    @PostMapping("/upload-file")
    Response<File> uploadFile(@RequestPart(name = "file", required = true) MultipartFile file,
                              @RequestPart(name = "command") FileUploadRequest request);

    @PostMapping("/upload-video")
    Response<File> uploadFile(@RequestPart(name = "file", required = true) MultipartFile file,
                              @RequestPart(name = "command") VideoUploadRequest request);

    @PostMapping("/upload-sound")
    Response<File> uploadFile(@RequestPart(name = "file", required = true) MultipartFile file,
                              @RequestPart(name = "command") SoundUploadRequest request);

    @PostMapping("/upload-imagea")
    Response<File> uploadFile(@RequestPart(name = "file", required = true) MultipartFile file,
                              @RequestPart(name = "command") ImageUploadRequest request);

    @PostMapping("/{id}/delete")
    Response<?> deleteFileById(@PathVariable UUID id);

    @GetMapping("/{id}")
    Response<File> getById(@PathVariable UUID id);
}

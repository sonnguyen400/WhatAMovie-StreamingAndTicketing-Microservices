package com.sonnguyen.snstorage.infrastructure.adaper.impl;

import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.snstorage.infrastructure.adaper.StorageAdapter;
import com.sonnguyen.snstorage.infrastructure.configuration.FileUploadResult;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class LocalAdapter implements StorageAdapter {

    private static final String DEFAULT_UPLOAD_DIR = "./uploads";

    @Override
    public FileUploadResult uploadFile(String bucketName, String originalFileName, byte[] data, Mimetype mimetype) {
        try {
            LocalDate now = LocalDate.now();
            String datePath = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            Path bucketPath = Paths.get(DEFAULT_UPLOAD_DIR, bucketName, datePath);
            if (!Files.exists(bucketPath)) {
                Files.createDirectories(bucketPath);
            }

            String extension = "";
            if (originalFileName != null && originalFileName.lastIndexOf('.') > 0) {
                extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            }

            String storedFileName = UUID.randomUUID().toString() + extension;
            Path filePath = bucketPath.resolve(storedFileName);

            Files.write(filePath, data);

            String relativePath = bucketName + "/" + datePath + "/" + storedFileName;
            // The fileUrl will typically point to a Spring WebMvc static mapping or controller
            String fileUrl = "/local/" + relativePath;

            return FileUploadResult.builder()
                    .externalId(relativePath)
                    .url(fileUrl)
                    .build();
        } catch (IOException e) {
            log.error("Failed to store file locally", e);
            throw new RuntimeException("Could not store file", e);
        }
    }

    @Override
    public byte[] getFile(String relativePath, String originalFilename) {
        try {
            Path filePath = Paths.get(DEFAULT_UPLOAD_DIR, relativePath);
            if (!Files.exists(filePath)) {
                throw new RuntimeException("File not found: " + relativePath);
            }
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            log.error("Failed to read file: {}", relativePath, e);
            throw new RuntimeException("Could not read file", e);
        }
    }

    @Override
    public StorageProvider getProvider() {
        return StorageProvider.LOCAL;
    }
}

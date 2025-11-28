package com.sonnguyen.snstorage.infrastructure.adaper.impl;

import com.cloudinary.Cloudinary;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snstorage.infrastructure.adaper.StorageAdapter;
import com.sonnguyen.snstorage.infrastructure.configuration.FileUploadResult;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CloudinaryAdapter implements StorageAdapter {
    Cloudinary cloudinary;

    @Override
    public FileUploadResult uploadFile(String bucketName, String objectName, byte[] data, Mimetype mimetype) {
        try {
            String id = IdUtils.nextStrId();
            Map<Object, Object> uploadResult = cloudinary.uploader().upload(data, Map.of(
                    "resource_type", "auto",
                    "public_id", id
            ));
            return FileUploadResult.builder()
                    .externalId(id)
                    .url(uploadResult.get("url").toString())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public byte[] getFile(String bucketName, String objectName) {
        return new byte[0];
    }
}

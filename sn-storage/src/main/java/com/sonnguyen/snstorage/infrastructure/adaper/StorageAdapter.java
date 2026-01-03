package com.sonnguyen.snstorage.infrastructure.adaper;

import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.snstorage.infrastructure.configuration.FileUploadResult;

public interface StorageAdapter {
    FileUploadResult uploadFile(String bucketName, String objectName, byte[] data, Mimetype mimetype);

    byte[] getFile(String bucketName, String objectName);
}

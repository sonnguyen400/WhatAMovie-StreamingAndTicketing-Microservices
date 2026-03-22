package com.sonnguyen.snstorage.infrastructure.adaper;

import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.snstorage.infrastructure.configuration.FileUploadResult;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;

public interface StorageAdapter {
    FileUploadResult uploadFile(String path, String originalFileName, byte[] data, Mimetype mimetype);

    byte[] getFile(String path, String originalFilename);

    StorageProvider getProvider();
}

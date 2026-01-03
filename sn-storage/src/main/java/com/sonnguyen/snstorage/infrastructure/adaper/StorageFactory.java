package com.sonnguyen.snstorage.infrastructure.adaper;

import com.sonnguyen.common.client.BadRequestError;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.snstorage.infrastructure.adaper.impl.CloudinaryAdapter;
import com.sonnguyen.snstorage.infrastructure.adaper.impl.MinIOAdapter;
import com.sonnguyen.snstorage.infrastructure.configuration.StorageProperties;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RefreshScope
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StorageFactory {
    private MinIOAdapter minIOAdapter;
    private CloudinaryAdapter cloudinaryAdapter;
    private StorageProperties storageProperties;

    public StorageAdapter getAdapter(StorageProvider storageProvider){
        return switch (storageProvider){
            case MINIO -> this.minIOAdapter;
            case CLOUDINARY -> this.cloudinaryAdapter;
        };
    }

    public StorageAdapter getDefault(){
        if(Objects.nonNull(this.storageProperties.getDefaultProvider())){
            return this.getAdapter(this.storageProperties.getDefaultProvider());
        }
        throw new ResponseException(BadRequestError.DEFAULT_STORAGE_NOT_CONFIGURED);
    }
}

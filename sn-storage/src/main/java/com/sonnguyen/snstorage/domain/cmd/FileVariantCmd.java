package com.sonnguyen.snstorage.domain.cmd;

import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.snstorage.domain.FileMetaData;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FileVariantCmd {
    private Boolean isOriginal;
    private Boolean isThumbnail;
    private BigInteger sizeBytes;
    private Mimetype mimetype;
    private Integer accessCounter;
    private FileMetaData metaData;
    private String externalUrl;
    private String externalId;
}

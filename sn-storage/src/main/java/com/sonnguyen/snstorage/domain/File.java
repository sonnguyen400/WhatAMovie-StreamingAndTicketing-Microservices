package com.sonnguyen.snstorage.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;
import com.sonnguyen.common.util.FileUtils;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snstorage.domain.cmd.FileUploadCmd;
import com.sonnguyen.snstorage.domain.cmd.FileVariantCmd;
import com.sonnguyen.snstorage.domain.cmd.ImageUploadCmd;
import com.sonnguyen.snstorage.domain.cmd.SoundUploadCmd;
import com.sonnguyen.snstorage.domain.cmd.VideoUploadCmd;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageColorMode;
import com.sonnguyen.snstorage.infrastructure.support.constant.ImageFormat;
import com.sonnguyen.snstorage.infrastructure.support.constant.VisualDefinition;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO for {@link com.sonnguyen.snstorage.infrastructure.persistence.entity.FileEntity}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class File extends AuditingDomain {
    protected UUID id;
    protected String originalName;
    protected String name;
    protected StorageProvider storageProvider;
    protected List<FileVariant> variants;

    public File(MultipartFile fileData, FileUploadCmd cmd, StorageProvider provider) {
        this.id = IdUtils.nextId();
        this.originalName = fileData.getOriginalFilename();
        this.name = fileData.getName();
        this.storageProvider = provider;
    }

    public FileVariantCmd extractDefaultVariantFromFile(MultipartFile fileData){
       return new FileVariantCmd();
    }
}

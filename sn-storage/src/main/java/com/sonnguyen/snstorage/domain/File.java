package com.sonnguyen.snstorage.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snstorage.domain.cmd.FileUploadCmd;
import com.sonnguyen.snstorage.domain.cmd.FileVariantCmd;
import com.sonnguyen.snstorage.infrastructure.support.enums.StorageProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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

    public FileVariantCmd extractDefaultVariantFromFile(MultipartFile fileData) {
        return new FileVariantCmd();
    }
}

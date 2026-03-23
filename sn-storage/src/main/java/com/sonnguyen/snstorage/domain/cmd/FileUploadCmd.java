package com.sonnguyen.snstorage.domain.cmd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@Data
public class FileUploadCmd {
    private String bucketName;
    private Boolean autoReduceDefinition;
}

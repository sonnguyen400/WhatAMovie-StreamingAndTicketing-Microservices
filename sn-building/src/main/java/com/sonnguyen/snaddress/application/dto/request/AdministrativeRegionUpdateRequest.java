package com.sonnguyen.snaddress.application.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class AdministrativeRegionUpdateRequest {
    private UUID parentId;

    @Size(max = 255)
    private String name;

    @Size(max = 50)
    private String code;

    @Size(max = 50)
    private String type;

    private Integer level;
}

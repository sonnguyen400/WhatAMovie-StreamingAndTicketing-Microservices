package com.sonnguyen.snaddress.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class AdministrativeRegionCreateRequest {
    @NotNull(message = "Country ID is required")
    private UUID countryId;

    private UUID parentId;

    @NotBlank(message = "Region name is required")
    @Size(max = 255)
    private String name;

    @Size(max = 50)
    private String code;

    @NotBlank(message = "Region type is required")
    @Size(max = 50)
    private String type;

    @NotNull(message = "Level is required")
    private Integer level;
}

package com.sonnguyen.snaddress.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CountryCreateRequest {
    @NotBlank(message = "Country name is required")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Country code is required")
    @Size(max = 10)
    private String code;

    @Size(max = 10)
    private String phoneCode;
}

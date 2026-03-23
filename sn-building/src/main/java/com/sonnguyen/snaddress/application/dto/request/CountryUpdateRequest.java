package com.sonnguyen.snaddress.application.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CountryUpdateRequest {
    @Size(max = 255)
    private String name;

    @Size(max = 10)
    private String code;

    @Size(max = 10)
    private String phoneCode;
}

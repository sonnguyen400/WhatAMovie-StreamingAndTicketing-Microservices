package com.sonnguyen.common.model.application.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrustedClientAuthRequest {
    @NotNull(message = "CLIENT_ID_REQUIRED")
    private String clientId;
    @NotNull(message = "CLIENT_SECRET_REQUIRED")
    private String clientSecret;
}

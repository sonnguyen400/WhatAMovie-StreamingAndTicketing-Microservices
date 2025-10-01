package com.sonnguyen.common.model.application.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrustedClientAuthResponse {
    private String accessToken;
    private String refreshToken;
}

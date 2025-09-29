package com.sonnguyen.sniam.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccessTokenResponse{
    private String accessToken;
    private String refreshToken;
    private Long expirationIn;
    private String issuer;
}

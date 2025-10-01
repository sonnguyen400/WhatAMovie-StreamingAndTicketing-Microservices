package com.sonnguyen.sniam.domain.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientCreateOrUpdateCmd {
    private String name;
    private String clientId;
    private String clientSecret;
    private String provider;
    private Boolean enabled;
}

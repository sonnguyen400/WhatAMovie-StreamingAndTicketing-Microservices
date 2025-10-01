package com.sonnguyen.sniam.domain.command;

import lombok.Data;

@Data
public class ClientLoginCmd {
    private String clientId;
    private String rawPassword;
}

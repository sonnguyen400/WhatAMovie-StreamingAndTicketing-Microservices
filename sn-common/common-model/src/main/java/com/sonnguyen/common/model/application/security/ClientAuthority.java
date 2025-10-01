package com.sonnguyen.common.model.application.security;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ClientAuthority extends UserAuthority{
    private String clientId;
    @Override
    public Boolean getClient() {
        return true;
    }
}

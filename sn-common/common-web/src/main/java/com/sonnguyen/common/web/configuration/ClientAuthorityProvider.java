package com.sonnguyen.common.web.configuration;

import com.sonnguyen.common.model.application.security.ClientAuthority;

public interface ClientAuthorityProvider {
    ClientAuthority getByClientId(String clientId);
}

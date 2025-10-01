package com.sonnguyen.sniam.infrastructure.configuration;


import com.sonnguyen.common.model.application.security.ClientAuthority;
import com.sonnguyen.common.web.configuration.ClientAuthorityProvider;
import com.sonnguyen.sniam.application.service.ClientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientAuthorityProviderImpl implements ClientAuthorityProvider {
    ClientService clientService;

    @Override
    public ClientAuthority getByClientId(String clientId) {
        return clientService.getClientAuthorityByClientId(clientId);
    }
}

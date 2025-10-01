package com.sonnguyen.sniam.presentation.rest.impl;

import com.nimbusds.jose.JOSEException;
import com.sonnguyen.common.model.application.request.TrustedClientAuthRequest;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.response.TrustedClientAuthResponse;
import com.sonnguyen.common.model.application.security.ClientAuthority;
import com.sonnguyen.sniam.application.service.ClientService;
import com.sonnguyen.sniam.presentation.rest.ClientController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class ClientControllerImpl implements ClientController {
    ClientService clientService;

    @Override
    public Map<String, Object> getJwks() {
        return this.clientService.getKey().toJSONObject();
    }

    @Override
    public Response<ClientAuthority> getClientAuthority(String clientId) {
        return Response.of(this.clientService.getClientAuthorityByClientId(clientId));
    }

    @Override
    public TrustedClientAuthResponse clientSecretLogin(TrustedClientAuthRequest request) throws JOSEException {
        return this.clientService.secretBasedClientLogin(request);
    }
}

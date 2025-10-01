package com.sonnguyen.sniam.presentation.rest;

import com.nimbusds.jose.JOSEException;
import com.sonnguyen.common.model.application.request.TrustedClientAuthRequest;
import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.response.TrustedClientAuthResponse;
import com.sonnguyen.common.model.application.security.ClientAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api")
public interface ClientController {

    @GetMapping("/v1/.well-known/jwks.json")
    Map<String, Object> getJwks();

    @GetMapping("/v1/client/{clientId}/authorities")
    Response<ClientAuthority> getClientAuthority(@PathVariable String clientId);

    @PostMapping("/v1/client/secret-login")
    TrustedClientAuthResponse clientSecretLogin(@RequestBody TrustedClientAuthRequest request) throws JOSEException;
}

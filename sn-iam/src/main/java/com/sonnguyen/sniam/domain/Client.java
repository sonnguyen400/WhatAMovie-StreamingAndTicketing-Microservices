package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.common.web.security.UserPasswordEncoder;
import com.sonnguyen.sniam.domain.command.ClientCreateOrUpdateCmd;
import com.sonnguyen.sniam.domain.command.ClientLoginCmd;
import com.sonnguyen.sniam.infrastructure.error.BadRequestError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class Client extends AuditingDomain {
    private UUID id;
    private String name;
    private String clientId;
    private String clientSecret;
    private String provider;
    private Boolean enabled;
    private Boolean deleted;

    public Client(ClientCreateOrUpdateCmd clientCreateOrUpdateCmd, UserPasswordEncoder encoder) {
        this.id = IdUtils.nextId();
        this.name = clientCreateOrUpdateCmd.getName();
        this.clientId = clientCreateOrUpdateCmd.getClientId();
        this.clientSecret = encoder.encode(clientCreateOrUpdateCmd.getClientSecret());
        this.provider = clientCreateOrUpdateCmd.getProvider();
        this.enabled = true;
        this.deleted = false;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void delete() {
        this.deleted = true;
    }

    public void login(ClientLoginCmd clientLoginCmd, UserPasswordEncoder passwordEncoder) {
        if (!Objects.equals(clientLoginCmd.getClientId(), this.clientId)) {
            throw new ResponseException(BadRequestError.CLIENT_NOT_AUTHENTICATED);
        }
        boolean matched = passwordEncoder.matches(clientLoginCmd.getRawPassword(), this.clientSecret);
        if (!matched) {
            throw new ResponseException(BadRequestError.CLIENT_NOT_AUTHENTICATED);
        }
    }
}

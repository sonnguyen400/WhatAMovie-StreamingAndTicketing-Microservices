package com.sonnguyen.common.web.security;

import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.util.DataUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UserAuthentication implements Authentication {
    private final UserAuthority principal;
    private UUID originId;
    private String originToten;
    private boolean authenticated;

    public UserAuthentication(UserAuthority userAuthority, String originToten) {
        this.principal = userAuthority;
        this.originId = userAuthority.getUserId();
        this.originToten = originToten;
        this.setAuthenticated(true);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.principal != null) {
            List<String> authorities = DataUtils.getOrDefault(principal.getAuthorities(), List.of());
            return authorities.stream().map(SimpleGrantedAuthority::new).toList();
        }
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return originToten;
    }

    @Override
    public Object getDetails() {
        return principal;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (Objects.isNull(this.principal)) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a UserAuthority instead");
        }
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return principal.getUserName();
    }

    public UUID getOriginId() {
        return originId;
    }

    public void setOriginId(UUID originId) {
        this.originId = originId;
    }

    public String getOriginToten() {
        return originToten;
    }

    public void setOriginToten(String originToten) {
        this.originToten = originToten;
    }

    @Override
    public String toString() {
        return "UserAuthentication{" +
                "principal=" + principal.toString() +
                ", originId=" + originId +
                ", originToten='" + originToten + '\'' +
                ", authenticated=" + authenticated +
                '}';
    }
}

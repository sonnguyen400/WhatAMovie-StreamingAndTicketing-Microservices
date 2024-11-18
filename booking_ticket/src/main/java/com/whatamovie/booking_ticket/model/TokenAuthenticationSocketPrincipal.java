package com.whatamovie.booking_ticket.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.jwt.Jwt;

import java.security.Principal;
@Getter
@Setter
public class TokenAuthenticationSocketPrincipal implements Principal {
    private String username;

    public TokenAuthenticationSocketPrincipal(Jwt jwt) {
        this.username=jwt.getSubject();
    }

    public TokenAuthenticationSocketPrincipal() {
    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return username;
    }
}

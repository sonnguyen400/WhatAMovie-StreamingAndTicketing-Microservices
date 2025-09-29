package com.sonnguyen.common.web.security;

import com.sonnguyen.common.model.application.security.UserAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class SecurityUtils {

    private SecurityUtils() {
        // Utility class
    }

    public static Optional<UserAuthority> getUserAuthority() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication() != null
                ? securityContext.getAuthentication().getPrincipal()
                : null;
        if (principal instanceof UserAuthority) {
            return Optional.of((UserAuthority) principal);
        }
        return Optional.empty();
    }

    public static Optional<UserAuthentication> getUserAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication() != null
                ? securityContext.getAuthentication().getPrincipal()
                : null;
        return Optional.ofNullable((UserAuthentication) principal);
    }

    public static UUID getCurrentUserId() {
        return getUserAuthority()
                .map(UserAuthority::getUserId)
                .orElse(null);
    }

    public static String getOriginToken() {
        return getUserAuthentication()
                .map(UserAuthentication::getOriginToten)
                .orElse(null);
    }

    public static UUID getOriginId() {
        return getUserAuthentication()
                .map(UserAuthentication::getOriginId)
                .orElse(null);
    }

    public static void setAuthentication(UserAuthentication userAuthentication) {
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}

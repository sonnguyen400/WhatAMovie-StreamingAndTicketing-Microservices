package com.sonnguyen.common.web.security.filter;

import com.nimbusds.jwt.JWTClaimsSet;
import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.web.configuration.JwtTokenProvider;
import com.sonnguyen.common.web.configuration.UserAuthorityProvider;
import com.sonnguyen.common.web.security.SecurityUtils;
import com.sonnguyen.common.web.security.UserAuthentication;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    JwtTokenProvider jwtTokenProvider;
    UserAuthorityProvider userAuthorityProvider;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenOpt = this.extractToken(request);
        if(tokenOpt.isPresent()){
            String accessToken = tokenOpt.get();
            JWTClaimsSet claimsSet = this.jwtTokenProvider.verify(accessToken);
            UserAuthority userAuthority = this.userAuthorityProvider.getById(UUID.fromString(claimsSet.getSubject()));
            UserAuthentication userAuthentication = new UserAuthentication(userAuthority, accessToken);
            SecurityUtils.setAuthentication(userAuthentication);
            log.info(userAuthentication.toString());
        }
        filterChain.doFilter(request, response);
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .map(it -> it.replace("Bearer ", ""));
    }

    private JWTClaimsSet extractClaim(String token) {
        return this.jwtTokenProvider.verify(token);
    }
}

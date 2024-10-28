package com.whatamovie.hall.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class OpenFeignTokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        JwtAuthenticationToken auth=((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication());
        if(auth!=null&&auth.getToken()!=null) {
            requestTemplate.header("Authorization","Bearer "+auth.getToken().getTokenValue());
        }
    }
}

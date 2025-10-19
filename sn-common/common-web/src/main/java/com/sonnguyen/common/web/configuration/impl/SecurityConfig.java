package com.sonnguyen.common.web.configuration.impl;

import com.sonnguyen.common.web.security.filter.AuthenticationTokenFilter;
import jakarta.servlet.Filter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {
    AuthenticationTokenFilter authenticationTokenFilter;

    @Bean
    public SecurityFilterChain securityConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers("/api/v1/authentication/login-password/**").permitAll()
                            .requestMatchers("/api/v1/authentication/register/**").permitAll()
                            .requestMatchers("/api/v1/authentication/reset-password/**").permitAll()
                            .requestMatchers("/api/v1/.well-known/jwks.json/**").permitAll()
                            .requestMatchers("/v3/api-docs**").permitAll()
                            .anyRequest().authenticated()
                    ;
                }).addFilterBefore((Filter) this.authenticationTokenFilter, (Class<? extends Filter>) UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}

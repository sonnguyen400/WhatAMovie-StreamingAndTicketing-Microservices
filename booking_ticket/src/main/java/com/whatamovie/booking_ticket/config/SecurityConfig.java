package com.whatamovie.booking_ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/ws**")
                        .permitAll()
                        .requestMatchers("/socket**","/test**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                ).oauth2ResourceServer((httpSecurityOAuth2ResourceServerConfigurer ->
                {httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults());}));
        return http.build();
    }
}

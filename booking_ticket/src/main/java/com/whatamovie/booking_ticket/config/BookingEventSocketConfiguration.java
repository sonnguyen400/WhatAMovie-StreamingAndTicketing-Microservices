package com.whatamovie.booking_ticket.config;

import com.whatamovie.booking_ticket.model.TokenAuthenticationSocketPrincipal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.MimeType;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookingEventSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    JwtDecoder jwtDecoder;

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        registry.addEndpoint("/socket")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/booking");
    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                SimpMessageHeaderAccessor simpMessageHeaderAccessor = SimpMessageHeaderAccessor.getAccessor(message,SimpMessageHeaderAccessor.class);
                if (simpMessageHeaderAccessor.getMessageType().equals(SimpMessageType.CONNECT)) {
                    List<String> tokens = simpMessageHeaderAccessor.getNativeHeader("Authorization");
                    if (tokens != null && !tokens.isEmpty()) {
                        String token = tokens.getFirst();
                        if (token.startsWith("Bearer ")) {
                            token = token.substring(7);
                            Jwt authenticationToken = jwtDecoder.decode(token);
                            JwtAuthenticationToken jwtAuthenticationToken=new JwtAuthenticationToken(authenticationToken);
                            SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
                            simpMessageHeaderAccessor.setContentType(MimeType.valueOf("application/json"));
                            simpMessageHeaderAccessor.setUser(new TokenAuthenticationSocketPrincipal(authenticationToken));
                            return message;
                        }
                    }
                    throw new RuntimeException("Auth Exception");
                }
                return message;
            }

        });
    }
}

package com.whatamovie.booking_ticket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;
@Component
@Slf4j
public class SocketAuthenticationInterceptor implements HandshakeInterceptor {
    @Autowired
    JwtDecoder jwtDecoder;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("before handshake");
        List<String> authHeader = request.getHeaders().get("Authorization");

        if(authHeader!=null && !authHeader.isEmpty()) {
            for(String auth : authHeader) {
                if(auth.startsWith("Bearer ")) {
                    String token=auth.substring(7);
                    log.debug(token);
                    try{
                        Jwt authenticationToken=jwtDecoder.decode(token);
                        if(authenticationToken!=null) {
                            log.debug(authenticationToken.toString());
                            attributes.put("token", token);
                            attributes.put("username", authenticationToken.getSubject());
                            return true;
                        }
                    }catch (Exception e) {
                        log.debug("error while decoding token");
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);
                        return false;
                    }
                }
            }
        }
        log.info("authHeader: is null");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return false;

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}

package com.sonnguyen.sniam.presentation.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api")
public interface ClientController {

    @GetMapping("/v1/.well-known/jwks.json")
    Map<String, Object> getJwks();
}

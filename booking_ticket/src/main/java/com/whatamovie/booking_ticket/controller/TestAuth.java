package com.whatamovie.booking_ticket.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestAuth {
    @GetMapping
    public Object test(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getClass());
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

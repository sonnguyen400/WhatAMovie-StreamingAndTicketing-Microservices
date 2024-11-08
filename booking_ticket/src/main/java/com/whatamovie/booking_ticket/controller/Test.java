package com.whatamovie.booking_ticket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping(value = "/api/v1/test")
    public String rest() {
        return "Hello World";
    }
}

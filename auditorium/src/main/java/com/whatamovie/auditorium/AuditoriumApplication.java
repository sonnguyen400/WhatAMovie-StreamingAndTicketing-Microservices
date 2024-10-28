package com.whatamovie.auditorium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuditoriumApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuditoriumApplication.class, args);
    }
}

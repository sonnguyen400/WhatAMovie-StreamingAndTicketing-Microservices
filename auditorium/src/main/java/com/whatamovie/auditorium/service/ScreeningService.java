package com.whatamovie.auditorium.service;

import com.whatamovie.auditorium.model.Screening;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "screening-service")
public interface ScreeningService {
    @GetMapping("/api/v1/screening/{id}")
    Screening findById(@PathVariable Long id) ;
}

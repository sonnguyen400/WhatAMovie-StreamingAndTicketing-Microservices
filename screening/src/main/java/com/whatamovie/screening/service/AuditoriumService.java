package com.whatamovie.screening.service;

import com.whatamovie.screening.exception.AuditoriumFallbackHandler;
import com.whatamovie.screening.model.Auditorium;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "auditorium-service"
)
public interface AuditoriumService {
    @GetMapping("/api/v1/auditorium")
    List<Auditorium> findAllByIds(@RequestParam List<Long> ids);
    @GetMapping("/api/v1/auditorium/{id}")
    Auditorium findById(@PathVariable Long id);
}

package com.whatamovie.auditorium.service;

import com.whatamovie.auditorium.model.Hall;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "hall-service"
)
public interface HallService {
    @GetMapping("/{id}")
    Hall findHallById(@PathVariable long id);
}

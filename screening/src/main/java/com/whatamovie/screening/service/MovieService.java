package com.whatamovie.screening.service;

import com.whatamovie.screening.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
    name = "movie-service"
)
public interface MovieService {
    @GetMapping("/api/v1/movie/{id}")
    Movie findById(@PathVariable  int id);
}

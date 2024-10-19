package com.whatamovie.screening.service;

import com.whatamovie.screening.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "movie-service",
        url = "${application.config.movie-url}"
)
public interface MovieService {
    @GetMapping("/{id}")
    Movie findById(@PathVariable  int id);
}

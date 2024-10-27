package com.whatamovie.movie.service;

import com.whatamovie.movie.model.Screening;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "screening-service"
)
public interface ScreeningService {
    @GetMapping("/api/v1/screening")
    List<Screening> findAllByMovieId(@RequestParam Long movieId);

}

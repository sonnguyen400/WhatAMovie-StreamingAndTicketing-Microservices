package com.whatamovie.screening.controller;

import com.whatamovie.screening.model.Screening;
import com.whatamovie.screening.service.MovieService;
import com.whatamovie.screening.service.ScreeningService;
import com.whatamovie.screening.viewmodel.ScreeningRequestVm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/screening")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ScreeningController {
    ScreeningService screeningService;
    MovieService movieService;

    @GetMapping
    public List<Screening> findAllByMovieId(
            @RequestParam Long movieId,
            @RequestParam(required = false) ZonedDateTime date,
            @RequestParam(required = false) Integer interval
    ){
        return screeningService.findAllByMovieId(movieId);
    }
    @PostMapping
    public Screening save(@RequestBody ScreeningRequestVm screening){
        return screeningService.insert(screening);
    }
}

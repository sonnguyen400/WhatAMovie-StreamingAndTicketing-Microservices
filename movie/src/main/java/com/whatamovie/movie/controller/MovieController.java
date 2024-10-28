package com.whatamovie.movie.controller;

import com.whatamovie.movie.model.Movie;
import com.whatamovie.movie.model.Screening;
import com.whatamovie.movie.repository.MovieRepository;
import com.whatamovie.movie.service.MovieService;
import com.whatamovie.movie.service.ScreeningService;
import com.whatamovie.movie.viewmodel.MovieRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/movie")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MovieController {
    MovieService movieService;
    ScreeningService screeningService;
    @GetMapping(value = "/{id}")
    public Movie findById(@PathVariable Long id) {
        return movieService.findById(id);
    }
    @GetMapping
    public Page<Movie> findAll(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "offset",required = false,defaultValue = "10") int offset
    ) {
        return  movieService.findAll(PageRequest.of(page,offset));
    }
    @PostMapping
    public Movie save(@RequestBody MovieRequest movie) {
        return movieService.insert(movie);
    }
    @PutMapping("/${id}")
    public Movie update(@PathVariable Long id,@RequestBody MovieRequest movie) {
        return movieService.updateById(id,movie);
    }
    @GetMapping(value = "/{id}/screening")
    public List<Screening> findByMovieId(@PathVariable Long id) {
        return  screeningService.findAllByMovieId(id);
    }
}

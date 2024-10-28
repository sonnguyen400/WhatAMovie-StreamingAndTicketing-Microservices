package com.whatamovie.movie.viewmodel;

import com.whatamovie.movie.model.Movie;

import java.time.ZonedDateTime;

public record MovieUpdateRequestVm(String title, String director, int year, int duration, String lang, String picture, String description,
                                   ZonedDateTime releaseDate, int ageLimit) {
    Movie toMovie(){
        return Movie
                .builder()
                .title(title)
                .director(director)
                .year(year)
                .duration(duration)
                .lang(lang)
                .picture(picture)
                .description(description)
                .releaseDate(releaseDate)
                .ageLimit(ageLimit)
                .build();
    }
}

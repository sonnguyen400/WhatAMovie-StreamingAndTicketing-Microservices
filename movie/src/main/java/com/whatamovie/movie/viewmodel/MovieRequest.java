package com.whatamovie.movie.viewmodel;

import com.whatamovie.movie.model.Movie;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public record MovieRequest(String title, String director, int year, int duration, String lang, String picture,
                           String description, boolean isRelease,
                           int releaseDate, int ageLimit) {
    public Movie toMovie() {
        return Movie.builder()
                .title(title)
                .year(year)
                .duration(duration)
                .lang(lang)
                .picture(picture)
                .description(description)
                .isRelease(isRelease)
                .releaseDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli(releaseDate), ZoneOffset.UTC))
                .ageLimit(ageLimit)
                .build();
    }
}

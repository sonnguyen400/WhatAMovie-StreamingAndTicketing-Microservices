package com.whatamovie.screening.viewmodel;

import com.whatamovie.screening.model.Screening;
import jakarta.annotation.Nonnull;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public record ScreeningRequestVm(int start, String description, int movieId) {
    public Screening toScreening(){
        return Screening.builder()
                .startTime(ZonedDateTime.ofInstant(Instant.ofEpochMilli(start),ZoneOffset.UTC))
                .description(description)
                .movie_id(movieId)
                .build();
    }
}

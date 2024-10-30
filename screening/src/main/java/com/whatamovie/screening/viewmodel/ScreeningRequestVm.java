package com.whatamovie.screening.viewmodel;

import com.whatamovie.screening.model.Screening;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public record ScreeningRequestVm(@Schema(description = "show time of movie") Long start,
                                 String description,
                                 Long movie_id,
                                 Long auditorium_id,
                                 @Schema(description = "leisure time after the movie is shown") Integer leisure_time_after) {
    public Screening toScreening() {
        return Screening.builder()
                .startTime(ZonedDateTime.ofInstant(Instant.ofEpochSecond(start), ZoneOffset.UTC))
                .description(description)
                .movie_id(movie_id)
                .auditorium_id(auditorium_id)
                .leisure_time_after(leisure_time_after)
                .build();
    }
}

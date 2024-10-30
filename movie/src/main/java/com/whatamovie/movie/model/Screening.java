package com.whatamovie.movie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Screening{
    private Long id;
    private ZonedDateTime startTime;
    private boolean isCancel;
    private String description;
    private int movie_id;
}

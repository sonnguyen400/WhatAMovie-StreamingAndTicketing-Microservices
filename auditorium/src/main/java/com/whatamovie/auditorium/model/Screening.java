package com.whatamovie.auditorium.model;

import jakarta.persistence.*;
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

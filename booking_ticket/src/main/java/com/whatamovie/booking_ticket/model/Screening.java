package com.whatamovie.booking_ticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Screening{
    private Long id;
    private ZonedDateTime startTime;
    private boolean isCancel;
    private String description;
    private Long movie_id;
    private Long auditorium_id;
    private Integer leisure_time_after;

}



package com.whatamovie.screening.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "screening")
public class Screening extends AbstractAuditEntity{
    @Id
    private Long id;
    private ZonedDateTime startTime;
    private boolean isCancel;
    private String description;
    private int movie_id;
}

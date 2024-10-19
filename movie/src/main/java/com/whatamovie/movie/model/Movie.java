package com.whatamovie.movie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String director;
    private int year;
    private int duration;
    private String lang;
    private String picture;
    private String description;
    private boolean isRelease;
    private ZonedDateTime releaseDate;
    private int ageLimit;
}

package com.whatamovie.auditorium.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auditorium extends AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String scheme;
    @OneToMany(mappedBy = "auditorium")
    private List<Seat> seats;
    private Long hall_id;
}

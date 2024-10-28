package com.whatamovie.screening.model;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auditorium extends AbstractAuditEntity{
    private Long id;
    private String name;
    private String description;
    private String scheme;
    private Long hall_id;
}

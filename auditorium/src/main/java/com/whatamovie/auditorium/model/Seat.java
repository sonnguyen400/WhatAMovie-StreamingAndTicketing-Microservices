package com.whatamovie.auditorium.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whatamovie.auditorium.constant.SeatStatus;
import com.whatamovie.auditorium.constant.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seat extends AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNo;
    private Integer rowNo;
    private SeatType type;
    private SeatStatus status;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @Column(name = "auditorium_id",insertable = false, updatable = false)
    private Long auditoriumId;
}

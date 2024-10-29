package com.whatamovie.auditorium.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "seat_reservation")
public class SeatReservation extends  AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private BigDecimal price;

    private Long screening_id;
}

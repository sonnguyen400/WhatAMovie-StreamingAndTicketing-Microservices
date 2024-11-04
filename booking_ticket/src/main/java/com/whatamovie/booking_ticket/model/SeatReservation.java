package com.whatamovie.booking_ticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.whatamovie.booking_ticket.constant.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seat_reservation")
public class SeatReservation extends  AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private Long auditorium_id;
    private Long screening_id;
    private Seat seat;
}

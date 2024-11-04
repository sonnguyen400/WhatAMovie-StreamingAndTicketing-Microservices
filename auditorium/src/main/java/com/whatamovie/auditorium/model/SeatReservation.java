package com.whatamovie.auditorium.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seat_reservation")
public class SeatReservation extends  AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "seat_id",insertable = false, updatable = false)
    @JsonIgnoreProperties({"auditorium"})
    @JsonBackReference
    private Seat seat;

    @Column(name = "seat_id")
    private Long seat_id;
    private BigDecimal price;
    @NotNull
    private Long screening_id;

    @NotNull
    @Column(name = "auditorium_id")
    private Long auditorium_id;
}

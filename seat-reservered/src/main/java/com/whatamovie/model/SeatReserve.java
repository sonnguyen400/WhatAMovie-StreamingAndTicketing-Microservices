package com.whatamovie.model;

import com.whatamovie.constant.SeatStatus;
import com.whatamovie.constant.SeatType;
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
public class SeatReserve extends AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    private String seatNo;
    private String rowNo;
    private SeatType seatType;
    private SeatStatus seatStatus;
    private int auditorium_id;
}

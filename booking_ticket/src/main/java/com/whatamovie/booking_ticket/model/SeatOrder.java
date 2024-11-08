package com.whatamovie.booking_ticket.model;

import com.whatamovie.booking_ticket.constant.SeatOrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
public class SeatOrder extends  AbstractAuditEntity{
    @Id
    Long id;
    String username;
    Long seat_reservation_id;
    Long screening_id;
    Seat seat;
    Screening screening;
    SeatOrderStatus seatOrderStatus;
}

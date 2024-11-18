package com.whatamovie.booking_ticket.model;

import com.whatamovie.booking_ticket.constant.SeatOrderStatus;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TickingSocketMessage {
    SeatOrderStatus status;
    Long seat_reservation_id;
    Long screening_id;
    Object user;
}

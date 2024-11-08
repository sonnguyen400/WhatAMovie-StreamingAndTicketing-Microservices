package com.whatamovie.booking_ticket.model;

import com.whatamovie.booking_ticket.constant.TickingSocketMessageState;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TickingSocketMessage {
    TickingSocketMessageState state;
    Long seat_reservation_id;
    Long screening_id;
    Object user;

}

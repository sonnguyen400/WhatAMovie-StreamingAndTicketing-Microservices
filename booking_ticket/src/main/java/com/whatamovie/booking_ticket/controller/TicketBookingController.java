package com.whatamovie.booking_ticket.controller;

import com.whatamovie.booking_ticket.constant.TickingSocketMessageState;
import com.whatamovie.booking_ticket.model.TickingSocketMessage;
import com.whatamovie.booking_ticket.service.SeatOrderedService;
import com.whatamovie.booking_ticket.vm.SeatReservationMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TicketBookingController {
    SeatOrderedService seatOrderedService;



    @MessageMapping("/booking.register/{screening_id}")
    @SendTo("/booking/public/{screening_id}")
    public TickingSocketMessage registerSeat(SeatReservationMessage seatReservationMessage, @DestinationVariable Long screening_id, SimpMessageHeaderAccessor headerAccessor) {
        return TickingSocketMessage.builder()
                .seat_reservation_id(seatReservationMessage.getSeat_reservation_id())
                .state(TickingSocketMessageState.REGISTER)
                .user(headerAccessor.getUser())
                .screening_id(screening_id)
                .build();
    }
    @MessageMapping("/booking.cancel/{screening_id}")
    @SendTo("/booking/public/{screening_id}")
    public TickingSocketMessage cancelSeatOrder(@DestinationVariable Long screening_id, @Payload SeatReservationMessage seatReservationCancelMessage, SimpMessageHeaderAccessor headerAccessor) {
        return TickingSocketMessage.builder()
                .seat_reservation_id(seatReservationCancelMessage.getSeat_reservation_id())
                .state(TickingSocketMessageState.CANCEL)
                .screening_id(screening_id)
                .user(headerAccessor.getUser())
                .build();
    }

}

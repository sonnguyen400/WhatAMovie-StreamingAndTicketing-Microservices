package com.whatamovie.booking_ticket.controller;

import com.whatamovie.booking_ticket.constant.SeatOrderStatus;
import com.whatamovie.booking_ticket.model.SeatOrder;
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
    public SeatOrder registerSeat(@Payload SeatReservationMessage seatReservationMessage, @DestinationVariable Long screening_id, SimpMessageHeaderAccessor headerAccessor) {
       log.debug("message {}", seatReservationMessage);
        return seatOrderedService.register(SeatOrder.builder()
                    .user(headerAccessor.getUser())
                    .screening_id(screening_id)
                    .status(SeatOrderStatus.BUSY)
                    .seat_reservation_id(seatReservationMessage.seat_reservation_id())
                    .build());
    }
    @MessageMapping("/booking.cancel/{screening_id}")
    @SendTo("/booking/public/{screening_id}")
    public TickingSocketMessage cancelSeatOrder(@Payload SeatReservationMessage seatReservationCancelMessage,@DestinationVariable Long screening_id,  SimpMessageHeaderAccessor headerAccessor) {
        return TickingSocketMessage.builder()
                .seat_reservation_id(seatReservationCancelMessage.seat_reservation_id())
                .status(SeatOrderStatus.FREE)
                .screening_id(screening_id)
                .user(headerAccessor.getUser())
                .build();
    }

}

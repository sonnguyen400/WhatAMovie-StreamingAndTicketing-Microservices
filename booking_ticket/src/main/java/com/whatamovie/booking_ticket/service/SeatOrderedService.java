package com.whatamovie.booking_ticket.service;

import com.whatamovie.booking_ticket.constant.SeatOrderStatus;
import com.whatamovie.booking_ticket.model.SeatOrder;
import com.whatamovie.booking_ticket.repository.SeatOrderRepository;
import com.whatamovie.booking_ticket.repository.SeatReservationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SeatOrderedService {
    SeatService seatService;
    ScreeningService screeningService;
    SeatReservationRepository seatReservationRepository;

    SeatOrderRepository seatOrderRepository;


    public SeatOrder placeSeat(SeatOrder seatOrder) {

        return seatOrderRepository.save(seatOrder);
    }
    public SeatOrder cancelSeat(Long seatOrderId) {
        return seatOrderRepository.findById(seatOrderId).map(seatOrder -> {
            seatOrder.setSeatOrderStatus(SeatOrderStatus.FREE);
        });
    }
}

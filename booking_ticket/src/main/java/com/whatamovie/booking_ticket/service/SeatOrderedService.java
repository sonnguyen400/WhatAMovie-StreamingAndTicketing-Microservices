package com.whatamovie.booking_ticket.service;

import com.whatamovie.booking_ticket.constant.SeatOrderStatus;
import com.whatamovie.booking_ticket.model.SeatOrder;
import com.whatamovie.booking_ticket.repository.SeatOrderRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SeatOrderedService {
    SeatService seatService;
    ScreeningService screeningService;

    SeatOrderRepository seatOrderRepository;

    public List<SeatOrder> findAllByScreeningId(Long screeningId) {
        return seatOrderRepository.findAllByScreening_id(screeningId);
    }
    public SeatOrder register(SeatOrder seatOrder) {
        List<SeatOrder> seatOrders= seatOrderRepository.findAllBySeatReservation_id(seatOrder.getSeat_reservation_id());
        if(seatOrders.isEmpty()){
            log.debug("Placing seat");
            return seatOrderRepository.save(seatOrder);
        }
        log.debug("Seat already exists");
        return seatOrder;
    }
    public SeatOrder cancelSeat(String seatOrderId) {
        return seatOrderRepository.findById(seatOrderId).map(seatOrder -> {
            seatOrder.setStatus(SeatOrderStatus.FREE);
            return seatOrderRepository.save(seatOrder);
        }).orElseThrow(()->new NotFoundException(String.format("Seat order with id {%s} not found", seatOrderId)));
    }
}

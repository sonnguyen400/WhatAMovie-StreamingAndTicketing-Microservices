package com.whatamovie.booking_ticket.service;

import com.whatamovie.booking_ticket.constant.SeatOrderStatus;
import com.whatamovie.booking_ticket.model.SeatOrder;
import com.whatamovie.booking_ticket.model.TickingSocketMessage;
import com.whatamovie.booking_ticket.model.TokenAuthenticationSocketPrincipal;
import com.whatamovie.booking_ticket.repository.SeatOrderRepository;
import com.whatamovie.booking_ticket.vm.SeatOrderCancelVm;
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
    public TickingSocketMessage register(SeatOrder seatOrder) {
        List<SeatOrder> seatOrders= seatOrderRepository.findAllBySeatReservation_id(seatOrder.getSeat_reservation_id());
        if(seatOrders.isEmpty()){
            seatOrder= seatOrderRepository.save(seatOrder);
            return TickingSocketMessage.builder()
                    .status(seatOrder.getStatus())
                    .seat_reservation_id(seatOrder.getSeat_reservation_id())
                    .screening_id(seatOrder.getScreening_id())
                    .status_code(200L)
                    .status_message("Success order")
                    .username(seatOrder.getUsername())
                    .build();
        }
        return TickingSocketMessage.builder()
                .status(SeatOrderStatus.FREE)
                .seat_reservation_id(seatOrder.getSeat_reservation_id())
                .screening_id(seatOrder.getScreening_id())
                .status_code(404L)
                .status_message("Failure order")
                .username(seatOrder.getUsername())
                .build();
    }
    public Integer cancelSeatOrderByReservation(SeatOrderCancelVm seatOrderCancelVm) {
        return seatOrderRepository.cancelSeatOrderByReservation(seatOrderCancelVm.session_id(),seatOrderCancelVm.seat_reservation_id());
    }
    public Integer cancelSeatOrderBySession(String session) {
        return seatOrderRepository.cancelSeatOrderBySessionId(session);
    }
}

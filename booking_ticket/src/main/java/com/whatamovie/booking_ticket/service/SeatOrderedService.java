package com.whatamovie.booking_ticket.service;

import com.whatamovie.booking_ticket.constant.SeatOrderStatus;
import com.whatamovie.booking_ticket.model.SeatOrder;
import com.whatamovie.booking_ticket.repository.SeatOrderRepository;
import com.whatamovie.booking_ticket.vm.SeatOrderPostVm;
import jakarta.ws.rs.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    public SeatOrder placeSeat(SeatOrderPostVm seatOrder) {
        return seatOrderRepository.save(seatOrder.toEntity());
    }
    public SeatOrder cancelSeat(Long seatOrderId) {
        return seatOrderRepository.findById(seatOrderId).map(seatOrder -> {
            seatOrder.setSeatOrderStatus(SeatOrderStatus.FREE);
            return seatOrderRepository.save(seatOrder);
        }).orElseThrow(()->new NotFoundException(String.format("Seat order with id {%s} not found", seatOrderId)));
    }
}

package com.whatamovie.auditorium.vm;

import com.whatamovie.auditorium.constant.SeatStatus;
import com.whatamovie.auditorium.constant.SeatType;
import com.whatamovie.auditorium.model.Seat;

public record SeatThumbnailVm(
        Long id,
        String seatNo,
        Integer rowNo,
        SeatType seatType,
        SeatStatus status
) {
    public Seat toEntity(){
        return Seat.builder()
                .id(id)
                .seatNo(seatNo)
                .type(seatType)
                .rowNo(rowNo)
                .status(status)
                .build();
    }
    public SeatThumbnailVm(Seat seat){
        this(seat.getId(),seat.getSeatNo(), seat.getRowNo(), seat.getType(),seat.getStatus());
    }
}

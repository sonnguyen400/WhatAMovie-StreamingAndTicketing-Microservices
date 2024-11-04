package com.whatamovie.booking_ticket.model;

import com.whatamovie.booking_ticket.constant.SeatStatus;
import com.whatamovie.booking_ticket.constant.SeatType;

public class Seat {
    private Long id;
    private String seatNo;
    private Integer rowNo;
    private SeatType type;
    private SeatStatus status;
    private Long auditorium_id;
}

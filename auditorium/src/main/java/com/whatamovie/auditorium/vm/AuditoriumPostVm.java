package com.whatamovie.auditorium.vm;

import com.whatamovie.auditorium.model.Auditorium;
import com.whatamovie.auditorium.model.Seat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AuditoriumPostVm (@NotNull String name, @NotNull Long hall_id, @NotNull String description, String scheme,@NotNull List<SeatThumbnailVm> seats){
    public Auditorium toEntity(){
        Auditorium auditorium= Auditorium.builder()
                .name(name)
                .hall_id(hall_id)
                .description(description)
                .scheme(scheme)
                .build();
        auditorium.setSeats(seats.stream().map(seat_->{
            Seat seat= seat_.toEntity();
             seat.setAuditorium(auditorium);
             return seat;
        }).toList());
        return  auditorium;
    }
}

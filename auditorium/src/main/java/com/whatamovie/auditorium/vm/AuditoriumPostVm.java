package com.whatamovie.auditorium.vm;

import com.whatamovie.auditorium.model.Auditorium;

public record AuditoriumPostVm (String name, long hall_id, String description, String scheme){
    public Auditorium toEntity(){
        return Auditorium.builder()
                .name(name)
                .hall_id(hall_id)
                .description(description)
                .scheme(scheme)
                .build();
    }
}

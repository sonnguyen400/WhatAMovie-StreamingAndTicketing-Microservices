package com.whatamovie.auditorium.vm;

import com.whatamovie.auditorium.model.Auditorium;

public record AuditoriumThumbnailVm(long id, String name, long hall_id) {
    AuditoriumThumbnailVm(Auditorium auditorium){
        this(auditorium.getId(), auditorium.getName(), auditorium.getHall_id());
    }
}

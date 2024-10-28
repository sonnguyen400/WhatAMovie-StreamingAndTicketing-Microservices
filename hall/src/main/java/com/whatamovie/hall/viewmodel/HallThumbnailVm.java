package com.whatamovie.hall.viewmodel;

import com.whatamovie.hall.model.Hall;

public record HallThumbnailVm(Long id, String name){
    public HallThumbnailVm(Hall hall) {
        this(hall.getId(), hall.getName());
    }
}

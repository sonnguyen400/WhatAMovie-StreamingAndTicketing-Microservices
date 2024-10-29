package com.whatamovie.auditorium.exception;

import com.whatamovie.auditorium.model.Hall;
import com.whatamovie.auditorium.service.HallService;

public class HandlerHallServiceFallback implements HallService {
    @Override
    public Hall findHallById(long id) {
        return null;
    }
}

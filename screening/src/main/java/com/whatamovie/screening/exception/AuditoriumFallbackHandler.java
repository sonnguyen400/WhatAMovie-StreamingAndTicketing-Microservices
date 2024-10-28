package com.whatamovie.screening.exception;

import com.whatamovie.screening.model.Auditorium;
import com.whatamovie.screening.service.AuditoriumService;
import org.springframework.stereotype.Component;

import java.util.List;

public class AuditoriumFallbackHandler implements AuditoriumService {
    @Override
    public List<Auditorium> findAllByIds(List<Long> ids) {
        return List.of();
    }
    @Override
    public Auditorium findById(Long id) {
        return null;
    }
}

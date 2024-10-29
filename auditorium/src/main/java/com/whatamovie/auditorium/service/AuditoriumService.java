package com.whatamovie.auditorium.service;

import com.whatamovie.auditorium.model.Auditorium;
import com.whatamovie.auditorium.repository.AuditoriumRepository;
import com.whatamovie.auditorium.vm.AuditoriumPostVm;
import com.whatamovie.common_component.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuditoriumService {
    AuditoriumRepository auditoriumRepository;
    HallService hallService;
    public Auditorium createAuditorium(AuditoriumPostVm auditorium) {
        if(hallService.findHallById(auditorium.hall_id())==null) throw new ResourceNotFoundException(String.format("Hall with id{%s} not found", auditorium.hall_id()));
        return auditoriumRepository.save(auditorium.toEntity());
    }

    public List<Auditorium> findAllByIds(List<Long> ids) {
        return auditoriumRepository.findAllById(ids);
    }

    public Auditorium findById(long id) {
        return auditoriumRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Auditorium with id{%s} not found", id)));
    }

    public List<Auditorium> findAll() {
        return auditoriumRepository.findAll();
    }

    public Auditorium updateById(long id, Auditorium auditorium) {
        return auditoriumRepository.findById(id).map((auditorium_)->{
            auditorium.setId(auditorium_.getId());
            return auditoriumRepository.save(auditorium);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("Auditorium with id{%s} not found", id)));
    }
}

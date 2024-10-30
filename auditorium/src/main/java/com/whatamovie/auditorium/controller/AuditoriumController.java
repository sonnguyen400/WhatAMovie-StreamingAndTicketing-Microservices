package com.whatamovie.auditorium.controller;

import com.whatamovie.auditorium.model.Auditorium;
import com.whatamovie.auditorium.service.AuditoriumService;
import com.whatamovie.auditorium.vm.AuditoriumPostVm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditorium")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuditoriumController {
    AuditoriumService auditoriumService;
    @GetMapping
    public List<Auditorium> findAllByIds(@RequestParam List<Long> ids) {
        return auditoriumService.findAllByIds(ids);
    }
    @GetMapping("/{id}")
    public Auditorium findById(@PathVariable Long id) {
        return  auditoriumService.findById(id);
    }
    @PostMapping
    public Auditorium createAuditorium(@RequestBody AuditoriumPostVm auditorium) {
        return auditoriumService.createAuditorium(auditorium);
    }
}

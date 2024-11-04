package com.whatamovie.auditorium.controller;

import com.whatamovie.auditorium.service.SeatService;
import com.whatamovie.auditorium.vm.SeatThumbnailVm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditorium")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class SeatController {
    SeatService seatService;
    @GetMapping(value = "/{auditorium_id}/seat")
    public List<SeatThumbnailVm> findAllByAuditoriumId(@PathVariable("auditorium_id") Long auditoriumId) {
        return seatService.findAllByAuditorium(auditoriumId);
    }
    @PutMapping(value = "/seat/{id}")
    public SeatThumbnailVm seatThumbnailVm(@PathVariable("id") Long id, @RequestBody SeatThumbnailVm seatThumbnailVm) {
        return seatService.updateById(id, seatThumbnailVm);
    }
    @GetMapping(value = "/seat/ids")
    public List<SeatThumbnailVm> findAllInIds(@RequestParam List<Long> ids) {
        return seatService.findAllIdIn(ids);
    }
}

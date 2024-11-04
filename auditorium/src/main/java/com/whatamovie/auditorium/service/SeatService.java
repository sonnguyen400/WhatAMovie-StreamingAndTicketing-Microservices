package com.whatamovie.auditorium.service;

import com.whatamovie.auditorium.model.Seat;
import com.whatamovie.auditorium.repository.SeatRepository;
import com.whatamovie.auditorium.vm.SeatThumbnailVm;
import com.whatamovie.common_component.exception.ResourceNotFoundException;
import com.whatamovie.common_component.utils.DataUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class SeatService {
    SeatRepository seatRepository;
    public List<SeatThumbnailVm> findAllByAuditorium(Long auditorium_id){
        return seatRepository.findAllByAuditorium_Id(auditorium_id).stream().map(SeatThumbnailVm::new).toList();
    }
    public SeatThumbnailVm updateById(Long id, SeatThumbnailVm seatThumbnailVm){
        return seatRepository.findById(id).map(seat_->{
            Seat seat= DataUtils.mapFieldsModifiedObject(seatThumbnailVm.toEntity(),seat_,Seat.class);
            seat.setId(id);
            return new SeatThumbnailVm(seatRepository.save(seat));
        }).orElseThrow(()->new ResourceNotFoundException(String.format("Seat with id{%s} not found",id)));
    }

    public List<SeatThumbnailVm> findAllIdIn(List<Long> ids){
        return  seatRepository.findAllByIdIn(ids).stream().map(SeatThumbnailVm::new).toList();
    }
    public List<SeatThumbnailVm> findAllByAuditoriumIdAndIdIn(Long auditorium_id,List<Long> ids){
        return seatRepository.findAllByAuditorium_IdAndIdIn(auditorium_id,ids).stream().map(SeatThumbnailVm::new).toList();
    }
}

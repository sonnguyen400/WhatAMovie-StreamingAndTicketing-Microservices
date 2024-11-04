package com.whatamovie.auditorium.service;

import com.whatamovie.auditorium.model.Screening;
import com.whatamovie.auditorium.model.SeatReservation;
import com.whatamovie.auditorium.repository.SeatReservationRepository;
import com.whatamovie.auditorium.vm.ListSeatReservationPostVm;
import com.whatamovie.auditorium.vm.SeatReservationPostVm;
import com.whatamovie.auditorium.vm.SeatThumbnailVm;
import com.whatamovie.common_component.exception.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class SeatReservationService {
    SeatReservationRepository seatReservationRepository;
    ScreeningService screeningService;
    SeatService seatService;
    public SeatReservation findById(Long id){
        return seatReservationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Seat reservation with id %s not found", id)));
    }
    public List<SeatReservation> findAllByScreeningAndAuditorium(Long screeningId,Long auditoriumId) {
        return seatReservationRepository.findAllByScreeningAndAuditorium(screeningId,auditoriumId);
    }

    public List<SeatReservation> createSeatReservationsForScreening(ListSeatReservationPostVm listSeatReservationPostVm) {
        List<Long> seatsId=listSeatReservationPostVm.seats().stream().map(SeatReservationPostVm::seat_id).toList();
        Screening screening=screeningService.findById(listSeatReservationPostVm.screening_id());
        List<SeatThumbnailVm> seat=seatService.findAllByAuditoriumIdAndIdIn(screening.getAuditorium_id(),seatsId);
        if(seat.size()!=listSeatReservationPostVm.seats().size()){
            Stream<String> idNaNExisted=listSeatReservationPostVm.seats()
                    .stream()
                    .map(SeatReservationPostVm::seat_id).filter(aLong ->seat.stream().noneMatch(seat_-> Objects.equals(seat_.id(), aLong))).map(Object::toString);
            throw new ResourceNotFoundException(String.format("Seats with there ids {%s} not match",idNaNExisted.collect(Collectors.joining())));
        }

        List<SeatReservation> seatReservationList = listSeatReservationPostVm.seats().stream().map((seat_) -> {
            SeatReservation seatReservation = seat_.toEntity();
            seatReservation.setScreening_id(screening.getId());
            seatReservation.setAuditorium_id(screening.getAuditorium_id());
            return seatReservation;
        }).toList();
        return seatReservationRepository.saveAll(seatReservationList);
    }
}

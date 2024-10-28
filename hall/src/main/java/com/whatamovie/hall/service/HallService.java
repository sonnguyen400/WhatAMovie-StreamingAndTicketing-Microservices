package com.whatamovie.hall.service;

import com.whatamovie.common_lib.exception.ResourceNotFoundException;
import com.whatamovie.common_lib.response.ResponsePage;
import com.whatamovie.hall.model.Hall;
import com.whatamovie.hall.repository.HallRepository;
import com.whatamovie.hall.viewmodel.HallPostVm;
import com.whatamovie.hall.viewmodel.HallThumbnailVm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HallService {
    HallRepository hallRepository;
    public Hall findById(long id){
        return hallRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("Product with id{%s} not found",id)));
    }
    public ResponsePage<HallThumbnailVm> findAll(PageRequest pageRequest){
        Page<Hall> result=hallRepository.findAll(pageRequest);
        return ResponsePage.<HallThumbnailVm>builder()
                .page(result.getNumber())
                .total(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .contents(result.getContent().stream().map(HallThumbnailVm::new).toList())
                .build();
    }
    public List<HallThumbnailVm> findAllByIds(List<Long> ids){
        return hallRepository.findAllByIdIn(ids).stream().map(HallThumbnailVm::new).toList();
    }
    public Hall createHall(HallPostVm hall){
        return hallRepository.save(hall.toEntity());
    }
    public void deleteById(long id){
        hallRepository.deleteById(id);
    }
    public Hall updateById(long id,Hall hall){
        return  hallRepository.updateById(id,hall);
    }
}

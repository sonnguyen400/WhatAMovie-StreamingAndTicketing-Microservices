package com.whatamovie.hall.controller;

import com.whatamovie.common_lib.response.ResponsePage;
import com.whatamovie.hall.model.Hall;
import com.whatamovie.hall.service.HallService;
import com.whatamovie.hall.viewmodel.HallPostVm;
import com.whatamovie.hall.viewmodel.HallThumbnailVm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hall")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class HallController {
    HallService hallService;
    @GetMapping
    public ResponsePage<HallThumbnailVm> findAllHalls(
            @RequestParam(name = "page",required = false,defaultValue = "0") int page,
            @RequestParam(name = "size",required = false,defaultValue = "20") int size,
            @RequestParam(name = "sort",required =false) List<String> sort,
            @RequestParam(name = "sortDirection",required = false) String sortDirection
    ){
        PageRequest pageRequest=PageRequest.of(page,size);
        if(sort!=null&& !sort.isEmpty() &&sortDirection!=null){
            String[] strings=new String[sort.size()];
            pageRequest=pageRequest.withSort(Sort.Direction.valueOf(sortDirection),sort.toArray(strings));
        }
        return hallService.findAll(pageRequest);
    }
    @GetMapping("/{id}")
    public Hall findHallById(@PathVariable long id) {
        return hallService.findById(id);
    }
    @GetMapping("/ids")
    public List<HallThumbnailVm> findHallsByIds(@RequestParam List<Long> ids) {
        return hallService.findAllByIds(ids);
    }
    @PostMapping
    public Hall createHall(@RequestBody HallPostVm hall) {
        return hallService.createHall(hall);
    }
    @PutMapping("/{id}")
    public Hall updateHall(@PathVariable long id,@RequestBody Hall hall) {
        return hallService.updateById(id,hall);
    }
    @DeleteMapping("/{id}")
    public void deleteHallById(@PathVariable long id) {
        hallService.deleteById(id);
    }
}

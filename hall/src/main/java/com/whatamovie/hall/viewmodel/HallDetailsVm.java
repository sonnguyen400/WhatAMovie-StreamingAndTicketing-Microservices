package com.whatamovie.hall.viewmodel;

import com.whatamovie.hall.model.Address;
import com.whatamovie.hall.model.Hall;

public record HallDetailsVm(Long id, String name, Address address) {
    Hall toEntity(){
        return new Hall(id, name, address);
    };
    public HallDetailsVm(Hall hall){
        this(hall.getId(), hall.getName(), hall.getAddress());
    }
}

package com.whatamovie.hall.viewmodel;

import com.whatamovie.hall.model.Address;
import com.whatamovie.hall.model.Hall;

public record HallPostVm (String name, Address address){
    public Hall toEntity(){
        Hall hall = new Hall();
        hall.setName(name);
        hall.setAddress(address);
        return hall;
    }
}

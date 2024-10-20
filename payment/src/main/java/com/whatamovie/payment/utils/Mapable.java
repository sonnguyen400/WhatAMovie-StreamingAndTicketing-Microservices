package com.whatamovie.payment.utils;

import java.util.Map;

public interface Mapable {
    MapableImp mapable=new MapableImp();
    default Map<String,Object> toMap(){
        return mapable.toMap(this);
    }
}

package com.whatamovie.hall.viewmodel;

import com.whatamovie.hall.model.Address;

public record AddressPostVm(String street, String city, String state, String zip, String country, String phone, String email, String addressLine1, String addressLine2) {
    Address toEntity(){
        return Address.builder()
                .street(street)
                .city(city)
                .state(state)
                .zip(zip)
                .country(country)
                .phone(phone)
                .email(email)
                .addressLine1(addressLine1)
                .addressLine2(addressLine2)
                .build();
    }
}

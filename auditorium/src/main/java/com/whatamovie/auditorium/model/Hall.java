package com.whatamovie.auditorium.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hall {
    private int id;
    private String name;
}

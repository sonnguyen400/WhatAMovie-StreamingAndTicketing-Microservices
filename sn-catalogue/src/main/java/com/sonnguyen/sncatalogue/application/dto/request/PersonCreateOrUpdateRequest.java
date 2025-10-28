package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonCreateOrUpdateRequest extends Request {
    private String fullName;
    private LocalDate dateOfBirth;
    private String description;
    private Double height;
    private Double weight;
    private String education;
}

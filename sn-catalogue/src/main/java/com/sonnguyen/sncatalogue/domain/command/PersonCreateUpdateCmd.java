package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.common.model.domain.command.InternationalizationCmd;
import com.sonnguyen.sncatalogue.infrastructure.constant.PersonRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PersonCreateUpdateCmd extends InternationalizationCmd {
    private String fullName;
    private LocalDate dateOfBirth;
    private String description;
    private Double height;
    private Double weight;
    private String education;
    private List<PersonRole> role;
}

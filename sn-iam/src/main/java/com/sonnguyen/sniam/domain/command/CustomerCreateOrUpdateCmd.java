package com.sonnguyen.sniam.domain.command;

import com.sonnguyen.sniam.infrastructure.support.enums.CustomerType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CustomerCreateOrUpdateCmd {
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private CustomerType type;
    private LocalDate dateOfBirth;
}

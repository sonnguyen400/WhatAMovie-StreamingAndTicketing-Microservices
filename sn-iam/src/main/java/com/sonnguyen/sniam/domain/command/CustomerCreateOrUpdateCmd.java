package com.sonnguyen.sniam.domain.command;

import com.sonnguyen.sniam.infrastructure.support.enums.CustomerType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerCreateOrUpdateCmd {
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private CustomerType type;
    private LocalDate dateOfBirth;
}

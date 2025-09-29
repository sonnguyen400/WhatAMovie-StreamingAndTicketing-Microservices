package com.sonnguyen.sniam.domain.command;

import com.sonnguyen.sniam.infrastructure.constant.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class UserCreateOrUpdateCmd {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private List<UUID> roleIds;
}

package com.sonnguyen.sniam.domain.command;

import com.sonnguyen.sniam.infrastructure.support.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserCreateOrUpdateCmd {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phoneNumber;
    private Boolean root;
    private Boolean locked;
    private Boolean enabled;
    private Boolean verified;
    private List<UUID> roleIds;
}

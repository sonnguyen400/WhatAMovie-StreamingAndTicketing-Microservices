package com.sonnguyen.sniam.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import com.sonnguyen.sniam.infrastructure.constant.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterUserRequest extends Request {
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private LocalDate dateOfBirth;
}

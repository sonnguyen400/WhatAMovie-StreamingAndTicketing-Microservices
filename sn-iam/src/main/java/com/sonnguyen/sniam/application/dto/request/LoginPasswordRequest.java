package com.sonnguyen.sniam.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginPasswordRequest extends Request {
    private String username;
    private String password;
}

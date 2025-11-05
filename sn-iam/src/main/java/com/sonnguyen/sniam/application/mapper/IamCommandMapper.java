package com.sonnguyen.sniam.application.mapper;

import com.sonnguyen.common.model.application.request.TrustedClientAuthRequest;
import com.sonnguyen.sniam.application.dto.request.RegisterUserRequest;
import com.sonnguyen.sniam.domain.command.ClientLoginCmd;
import com.sonnguyen.sniam.domain.command.UserCreateOrUpdateCmd;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IamCommandMapper {
    UserCreateOrUpdateCmd from(RegisterUserRequest request);

    ClientLoginCmd from(TrustedClientAuthRequest request);
}

package com.sonnguyen.snnotification.application.mapper;

import com.sonnguyen.common.model.application.request.notification.NotificationCreateRequest;
import com.sonnguyen.snnotification.domain.cmd.NotificationCreateCmd;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationDTOMapper {
    NotificationCreateCmd from(NotificationCreateRequest request);
}

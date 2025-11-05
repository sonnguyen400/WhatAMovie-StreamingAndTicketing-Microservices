package com.sonnguyen.snnotification.infrastructure.mongo.mapper;

import com.sonnguyen.common.data.mongo.mapper.DocumentMapper;
import com.sonnguyen.snnotification.domain.Notification;
import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationDocumentMapper extends DocumentMapper<Notification, NotificationDocument> {
}

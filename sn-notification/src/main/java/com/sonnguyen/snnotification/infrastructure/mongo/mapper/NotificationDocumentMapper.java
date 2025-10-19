package com.sonnguyen.snnotification.infrastructure.mongo.mapper;

import com.sonnguyen.common.data.mongo.mapper.DocumentMapper;
import com.sonnguyen.snnotification.domain.Notification;
import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationDocumentMapper extends DocumentMapper<Notification, NotificationDocument> {
}

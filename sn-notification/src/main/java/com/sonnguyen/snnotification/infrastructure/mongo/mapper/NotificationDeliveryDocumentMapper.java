package com.sonnguyen.snnotification.infrastructure.mongo.mapper;

import com.sonnguyen.common.data.mongo.mapper.DocumentMapper;
import com.sonnguyen.snnotification.domain.NotificationDelivery;
import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDeliveryDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationDeliveryDocumentMapper extends DocumentMapper<NotificationDelivery, NotificationDeliveryDocument> {
}

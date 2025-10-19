package com.sonnguyen.snnotification.infrastructure.domainrepository;

import com.sonnguyen.common.data.mongo.domain.repository.AbstractDomainRepository;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.snnotification.domain.Notification;
import com.sonnguyen.snnotification.domain.NotificationDelivery;
import com.sonnguyen.snnotification.domain.repository.NotificationRepository;
import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDeliveryDocument;
import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDocument;
import com.sonnguyen.snnotification.infrastructure.mongo.mapper.NotificationDeliveryDocumentMapper;
import com.sonnguyen.snnotification.infrastructure.mongo.mapper.NotificationDocumentMapper;
import com.sonnguyen.snnotification.infrastructure.mongo.repository.NotificationDeliveryDocumentRepository;
import com.sonnguyen.snnotification.infrastructure.mongo.repository.NotificationDocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public class NotificationRepositoryImpl extends AbstractDomainRepository<Notification, NotificationDocument, UUID>
        implements NotificationRepository {
    private NotificationDocumentRepository notificationDocumentRepository;
    private NotificationDocumentMapper notificationDocumentMapper;
    private NotificationDeliveryDocumentRepository notificationDeliveryDocumentRepository;
    private NotificationDeliveryDocumentMapper notificationDeliveryDocumentMapper;

    public NotificationRepositoryImpl(NotificationDocumentRepository notificationDocumentRepository,
                                      NotificationDeliveryDocumentRepository notificationDeliveryDocumentRepository,
                                      NotificationDeliveryDocumentMapper notificationDeliveryDocumentMapper,
                                      NotificationDocumentMapper notificationDocumentMapper) {
        super(notificationDocumentRepository, notificationDocumentMapper);
        this.notificationDocumentRepository = notificationDocumentRepository;
        this.notificationDocumentMapper = notificationDocumentMapper;
        this.notificationDeliveryDocumentRepository = notificationDeliveryDocumentRepository;
        this.notificationDeliveryDocumentMapper = notificationDeliveryDocumentMapper;
    }

    @Override
    public Collection<Notification> saveAll(Collection<Notification> domains) {
        List<NotificationDelivery> notificationDeliveries = new ArrayList<>();
        for (Notification notification : domains) {
            if (CollectionUtils.isNotEmpty(notification.getDeliveries())) {
                notificationDeliveries.addAll(notification.getDeliveries());
            }
        }
        Collection<NotificationDocument> notificationDocuments = this.notificationDocumentMapper.toDocument(domains);
        Collection<NotificationDeliveryDocument> notificationDeliveryDocuments = this.notificationDeliveryDocumentMapper
                .toDocument(notificationDeliveries);
        this.notificationDocumentRepository.saveAll(notificationDocuments);
        this.notificationDeliveryDocumentRepository.saveAll(notificationDeliveryDocuments);
        return super.saveAll(domains);
    }
}

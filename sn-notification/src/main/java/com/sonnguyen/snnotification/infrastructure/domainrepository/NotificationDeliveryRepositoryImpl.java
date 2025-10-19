package com.sonnguyen.snnotification.infrastructure.domainrepository;

import com.sonnguyen.common.data.mongo.domain.repository.AbstractDomainRepository;
import com.sonnguyen.snnotification.domain.Notification;
import com.sonnguyen.snnotification.domain.NotificationDelivery;
import com.sonnguyen.snnotification.domain.repository.NotificationDeliveryRepository;
import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDeliveryDocument;
import com.sonnguyen.snnotification.infrastructure.mongo.mapper.NotificationDeliveryDocumentMapper;
import com.sonnguyen.snnotification.infrastructure.mongo.mapper.NotificationDocumentMapper;
import com.sonnguyen.snnotification.infrastructure.mongo.repository.NotificationDeliveryDocumentRepository;
import com.sonnguyen.snnotification.infrastructure.mongo.repository.NotificationDocumentRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class NotificationDeliveryRepositoryImpl
        extends AbstractDomainRepository<NotificationDelivery, NotificationDeliveryDocument, UUID>
        implements NotificationDeliveryRepository {

    private NotificationDeliveryDocumentRepository notificationDeliveryDocumentRepository;
    private NotificationDeliveryDocumentMapper notificationDeliveryDocumentMapper;
    private NotificationDocumentRepository notificationDocumentRepository;
    private NotificationDocumentMapper notificationDocumentMapper;

    public NotificationDeliveryRepositoryImpl(NotificationDeliveryDocumentRepository notificationDeliveryDocumentRepository,
                                              NotificationDeliveryDocumentMapper notificationDeliveryDocumentMapper,
                                              NotificationDocumentRepository notificationDocumentRepository,
                                              NotificationDocumentMapper notificationDocumentMapper) {
        super(notificationDeliveryDocumentRepository, notificationDeliveryDocumentMapper);
        this.notificationDeliveryDocumentRepository = notificationDeliveryDocumentRepository;
        this.notificationDeliveryDocumentMapper = notificationDeliveryDocumentMapper;
        this.notificationDocumentRepository = notificationDocumentRepository;
        this.notificationDocumentMapper = notificationDocumentMapper;
    }

    @Override
    public Collection<NotificationDelivery> enrichAll(Collection<NotificationDelivery> domains) {
        Set<UUID> notificationIds = new HashSet<>();
        domains.forEach(it -> {
            if (Objects.nonNull(it.getNotificationId())) {
                notificationIds.add(it.getNotificationId());
            }
        });
        Map<UUID, Notification> notificationMaps = this.notificationDocumentMapper
                .toDomain(this.notificationDocumentRepository.findAllById(notificationIds))
                .stream()
                .collect(Collectors.toMap(Notification::getId, it -> it));
        domains.forEach(it -> {
            if (Objects.nonNull(it.getNotificationId())) {
                Optional.ofNullable(notificationMaps.getOrDefault(it.getNotificationId(), null))
                        .ifPresent(it::enrichNotification);
            }
        });
        return domains;
    }
}

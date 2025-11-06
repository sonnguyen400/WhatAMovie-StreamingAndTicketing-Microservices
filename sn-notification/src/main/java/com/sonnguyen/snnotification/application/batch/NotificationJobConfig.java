package com.sonnguyen.snnotification.application.batch;

import com.sonnguyen.common.model.infrastructure.support.enums.NotificationChanel;
import com.sonnguyen.common.model.infrastructure.support.enums.NotificationDeliveryStatus;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.snnotification.application.dto.request.MailBuilder;
import com.sonnguyen.snnotification.application.dto.response.MailRequestResult;
import com.sonnguyen.snnotification.domain.Notification;
import com.sonnguyen.snnotification.domain.NotificationAttachment;
import com.sonnguyen.snnotification.domain.NotificationDelivery;
import com.sonnguyen.snnotification.domain.query.NotificationDeliverySearchQuery;
import com.sonnguyen.snnotification.domain.repository.NotificationDeliveryRepository;
import com.sonnguyen.snnotification.infrastructure.apdapter.EmailServiceAdapter;
import com.sonnguyen.snnotification.infrastructure.constants.JobParamConstants;
import com.sonnguyen.snnotification.infrastructure.mongo.document.NotificationDeliveryDocument;
import com.sonnguyen.snnotification.infrastructure.mongo.mapper.NotificationDeliveryDocumentMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoPagingItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationJobConfig {
    NotificationDeliveryRepository notificationDeliveryRepository;
    NotificationDeliveryDocumentMapper notificationDeliveryDocumentMapper;
    EmailServiceAdapter emailServiceAdapter;

    @Bean
    public Job launchNotificationJob(JobRepository jobRepository, Step notificationStep) {
        return new JobBuilder("launchNotificationJob", jobRepository)
                .start(notificationStep)
                .build();
    }

    @Bean
    public Step notificationStep(JobRepository jobRepository,
                                 PlatformTransactionManager transactionManager,
                                 ItemReader<NotificationDeliveryDocument> notificationDeliveryItemReader,
                                 ItemProcessor<NotificationDeliveryDocument, NotificationDelivery> notificationDeliveryItemProcessor,
                                 ItemWriter<NotificationDelivery> notificationDeliveryDocumentItemWriter) {
        return new StepBuilder("notificationStep", jobRepository)
                .<NotificationDeliveryDocument, NotificationDelivery>chunk(500, transactionManager)
                .reader(notificationDeliveryItemReader)
                .processor(notificationDeliveryItemProcessor)
                .writer(notificationDeliveryDocumentItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public MongoPagingItemReader<NotificationDeliveryDocument> notificationDeliveryItemReader(
            MongoTemplate mongoTemplate,
            @Value("#{jobParameters}") Map<String, Object> params
    ) {
        NotificationDeliverySearchQuery query = this.buildJobParams(params);
        MongoPagingItemReader<NotificationDeliveryDocument> reader = new MongoPagingItemReader<>();
        reader.setTemplate(mongoTemplate);
        reader.setTargetType(NotificationDeliveryDocument.class);
        reader.setQuery("{ status: ?0, chanel: ?1, notification_id: {$in : ?2}}");
        reader.setParameterValues(List.of(NotificationDeliveryStatus.DELIVERED, query.getChanel(), query.getNotificationIds()));
        return reader;
    }

    @Bean
    public ItemProcessor<NotificationDeliveryDocument, NotificationDelivery> notificationDeliveryItemProcessor() {
        return this.notificationDeliveryDocumentMapper::toDomain;
    }

    @Bean
    @StepScope
    public ItemWriter<NotificationDelivery> notificationDeliveryItemWriter(
            @Value("#{jobParameters}") Map<String, Object> params
    ) {
        return items -> {
            this.notificationDeliveryRepository.enrichAll((Collection<NotificationDelivery>) items.getItems());
            NotificationDeliverySearchQuery query = this.buildJobParams(params);
            if (items.isEmpty()) return;
            if (CollectionUtils.isNotEmpty(items.getErrors())) return;
            if (NotificationChanel.EMAIL.equals(query.getChanel())) {
                this.sendEmailNotification(items.getItems());
            } else if (NotificationChanel.SMS.equals(query.getChanel())) {
                this.sendSMSNotification(items.getItems());
            } else if (NotificationChanel.PUSH.equals(query.getChanel())) {
                this.sendPushNotification(items.getItems());
            }
        };
    }

    private void sendPushNotification(List<? extends NotificationDelivery> items) {
    }

    private void sendSMSNotification(List<? extends NotificationDelivery> items) {
    }


    private void sendEmailNotification(List<? extends NotificationDelivery> notificationDelivery) {
        Map<UUID, List<NotificationDelivery>> notificationMap = notificationDelivery.stream()
                .collect(Collectors.groupingBy(NotificationDelivery::getNotificationId));
        for (List<NotificationDelivery> deliveries : notificationMap.values()) {
            List<String> emails = deliveries.stream().map(NotificationDelivery::getAddress).toList();
            Notification notification = deliveries.getFirst().getNotification();
            if (Objects.nonNull(notification)) {
                List<MultipartFile> attachments = notification.getAttachments().stream()
                        .map(NotificationAttachment::getData).toList();
                MailBuilder mailBuilder = MailBuilder.builder()
                        .emails(emails)
                        .title(notification.getTitle())
                        .content(notification.getContent())
                        .mimetype(notification.getContentType().toMimeType())
                        .attachments(attachments)
                        .build();
                MailRequestResult result = this.emailServiceAdapter.sendEmail(mailBuilder);
                if (result.isSuccess()) {
                    deliveries.forEach(NotificationDelivery::delivered);
                } else {
                    deliveries.forEach(NotificationDelivery::failed);
                }
            }
        }
    }


    private NotificationDeliverySearchQuery buildJobParams(Map<String, Object> jobParams) {
        return NotificationDeliverySearchQuery.builder()
                .notificationIds((List<UUID>) jobParams.get(JobParamConstants.NOTIFICATiON_IDS))
                .chanel((NotificationChanel) jobParams.get(JobParamConstants.NOTIFICATiON_CHANEL))
                .build();
    }

}

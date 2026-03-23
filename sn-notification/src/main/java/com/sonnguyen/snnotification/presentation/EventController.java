package com.sonnguyen.snnotification.presentation;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snnotification.application.dto.request.NotificationSearchRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.Notification;
import java.util.List;

@RequestMapping("/api")
public interface EventController {

    @GetMapping("/v1/me/notification")
    PagingResponse<List<Notification>> getCurrentUserNotification(NotificationSearchRequest request);

}

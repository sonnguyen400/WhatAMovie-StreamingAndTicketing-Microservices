package com.sonnguyen.snnotification.presentation;

import java.util.List;

import javax.management.Notification;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snnotification.application.dto.request.NotificationSearchRequest;

@RequestMapping("/api")
public interface EventController {

	@GetMapping("/v1/me/notification")
	PagingResponse<List<Notification>> getCurrentUserNotification(NotificationSearchRequest request);

}

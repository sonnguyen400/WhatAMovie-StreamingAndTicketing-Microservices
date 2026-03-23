package com.sonnguyen.snnotification.presentation.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snnotification.application.dto.request.NotificationSearchRequest;
import com.sonnguyen.snnotification.presentation.EventController;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;

import javax.management.Notification;
import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class EventControllerImpl implements EventController {

    @Override
    public PagingResponse<List<Notification>> getCurrentUserNotification(NotificationSearchRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}

package com.sonnguyen.snnotification.presentation.impl;

import com.sonnguyen.common.model.application.response.PagingResponse;
import com.sonnguyen.snnotification.presentation.EventController;
import org.springframework.stereotype.Controller;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventControllerImpl implements EventController {

    @Override
    public PagingResponse<List<Notification>> getCurrentUserNotification() {
        return PagingResponse.of(new ArrayList<Notification>()).build();
    }
}

package com.sonnguyen.snnotification.application.dto.request;

import com.sonnguyen.common.model.application.request.PagingRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationSearchRequest extends PagingRequest {

    private Boolean hasReaded;
}

package com.sonnguyen.common.web.constant;

public enum ServiceConstant {
    IAM_CLIENT("iam-service", "123456aA@"),
    NOTIFICATION_CLIENT("notification-client", "123456aA@");

    public final String clientId;
    public final String clientSecret;

    ServiceConstant(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}

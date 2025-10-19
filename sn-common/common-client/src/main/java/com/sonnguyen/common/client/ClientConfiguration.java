package com.sonnguyen.common.client;

import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = {IAMClient.class})
public class ClientConfiguration {
}

package com.sonnguyen.common.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FeignClientConfig implements RequestInterceptor {
    ClientTokenProvider clientTokenProvider;

    @Override
    public void apply(RequestTemplate requestTemplate) {

    }
}

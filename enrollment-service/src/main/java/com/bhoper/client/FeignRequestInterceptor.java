package com.bhoper.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    public static String token;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header("Authorization", token);
    }
}

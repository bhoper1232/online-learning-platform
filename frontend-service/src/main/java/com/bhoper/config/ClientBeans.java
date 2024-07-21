package com.bhoper.config;

import com.bhoper.client.RestClientCoursesRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {


    @Bean
    public RestClientCoursesRestClient productsRestClient() {
        return new RestClientCoursesRestClient(RestClient.builder().build());
    }

}

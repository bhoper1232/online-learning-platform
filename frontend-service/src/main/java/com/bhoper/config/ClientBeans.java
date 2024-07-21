package com.bhoper.config;

import com.bhoper.client.RestClientCoursesRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class ClientBeans {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthorizedClientRepository authorizedClientRepository;


    @Bean
    public RestClientCoursesRestClient productsRestClient() {
        return new RestClientCoursesRestClient(RestClient.builder().build(), clientRegistrationRepository, authorizedClientRepository);
    }

}

package com.bhoper.client;

import com.bhoper.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
public class RestClientCoursesRestClient implements CoursesRestClient {

    private static final ParameterizedTypeReference<List<Course>> PRODUCTS_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private RestClient restClient;

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public RestClientCoursesRestClient(RestClient restClient,
                                       ClientRegistrationRepository clientRegistrationRepository,
                                       OAuth2AuthorizedClientRepository authorizedClientRepository) {
        this.authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);
        this.restClient = RestClient.builder()
                .requestInterceptor((request, body, execution) -> {
                    if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                        String token = this.authorizedClientManager.authorize(OAuth2AuthorizeRequest
                                .withClientRegistrationId("greetings-app-authorization-code")
                                .principal(SecurityContextHolder.getContext().getAuthentication())
                                .build()).getAccessToken().getTokenValue();

                        request.getHeaders().setBearerAuth(token);
                    }

                    return execution.execute(request, body);
                })
                .build();
    }

    @Override
    public List<Course> findAllCourses() {
        return this.restClient.get()
                .uri("http://localhost:8080/courses")
                .retrieve()
                .body(PRODUCTS_TYPE_REFERENCE);
    }
}

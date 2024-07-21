package com.bhoper.client;

import com.bhoper.model.Course;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class RestClientCoursesRestClient implements CoursesRestClient {

    private static final ParameterizedTypeReference<List<Course>> PRODUCTS_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private RestClient restClient;

    public RestClientCoursesRestClient(RestClient restClient) {
        this.restClient = RestClient.builder().build();
    }

    @Override
    public List<Course> findAllCourses() {
        return this.restClient.get()
                .uri("http://localhost:8080/courses")
                .retrieve()
                .body(PRODUCTS_TYPE_REFERENCE);
    }
}

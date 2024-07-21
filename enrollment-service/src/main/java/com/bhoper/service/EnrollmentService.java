package com.bhoper.service;

import com.bhoper.dto.EnrollmentCreateRequest;
import com.bhoper.model.Course;
import com.bhoper.model.Enrollment;
import com.bhoper.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private RestClient restClient;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;

        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public Enrollment createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest) {
        Course course = this.restClient.get()
                .uri("/courses/{id}".formatted(enrollmentCreateRequest.courseId()))
                .retrieve()
                .body(Course.class);
        if (course != null) {
            Enrollment enrollment = Enrollment.builder()
                    .username(enrollmentCreateRequest.username())
                    .status(enrollmentCreateRequest.status())
                    .courseId(enrollmentCreateRequest.courseId())
                    .build();
            return this.enrollmentRepository.save(enrollment);
        } else {
            throw new RuntimeException("Course with %s id doesn't exist".formatted(enrollmentCreateRequest.courseId()));
        }
    }
}

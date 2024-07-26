package com.bhoper.service;

import com.bhoper.client.CourseClient;
import com.bhoper.dto.EnrollmentCreateRequest;
import com.bhoper.model.Course;
import com.bhoper.model.Enrollment;
import com.bhoper.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final CourseClient courseClient;

    public boolean createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest) {
        Course course = this.courseClient.getCourseById(enrollmentCreateRequest.courseId());
        if (course != null) {
            Enrollment enrollment = Enrollment.builder()
                    .username(enrollmentCreateRequest.username())
                    .status(enrollmentCreateRequest.status())
                    .courseId(enrollmentCreateRequest.courseId())
                    .build();
            this.enrollmentRepository.save(enrollment);
            return true;
        } else {
            throw new RuntimeException("Course with %s id doesn't exist".formatted(enrollmentCreateRequest.courseId()));
        }
    }
}

package com.bhoper.service;

import com.bhoper.client.course.Course;
import com.bhoper.client.course.CourseClient;
import com.bhoper.dto.EnrollmentCreateRequest;
import com.bhoper.model.Enrollment;
import com.bhoper.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final CourseClient courseClient;
    private final EnrollmentRepository enrollmentRepository;

    public boolean createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest) {
        System.out.println(enrollmentCreateRequest);
//        Course course = this.courseClient.getCourseById(enrollmentCreateRequest.courseId());
        Course course = null;
        if (course != null) {
            Enrollment enrollment = Enrollment.builder()
                    .username(enrollmentCreateRequest.username())
                    .status(enrollmentCreateRequest.status())
                    .courseId(enrollmentCreateRequest.courseId())
                    .build();
            this.enrollmentRepository.save(enrollment);
            return true;
        } else {
            return true;
//            throw new RuntimeException("Course with id %s doesn't exist".formatted(enrollmentCreateRequest.courseId()));
        }
    }
}

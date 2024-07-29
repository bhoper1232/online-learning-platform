package com.bhoper.service;

import com.bhoper.client.course.Course;
import com.bhoper.client.course.CourseClient;
import com.bhoper.dto.EnrollmentCreateRequest;
import com.bhoper.model.Enrollment;
import com.bhoper.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final CourseClient courseClient;
    private final EnrollmentRepository enrollmentRepository;

    public boolean createEnrollment(EnrollmentCreateRequest enrollmentCreateRequest) {
        System.out.println(enrollmentCreateRequest);
        Course course = this.courseClient.getCourseById(enrollmentCreateRequest.courseId());
        if (course != null && !this.enrollmentRepository.existsEnrollmentByUsernameAndCourseId(enrollmentCreateRequest.username(), enrollmentCreateRequest.courseId())) {
            Enrollment enrollment = Enrollment.builder()
                    .username(enrollmentCreateRequest.username())
                    .status(enrollmentCreateRequest.status())
                    .courseId(enrollmentCreateRequest.courseId())
                    .build();
            this.enrollmentRepository.save(enrollment);
            return true;
        } else {
            return false;
        }
    }

    public List<Enrollment> findEnrollmentsByUsername(String username) {
        return this.enrollmentRepository.findEnrollmentsByUsername(username);
    }
}
